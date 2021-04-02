package com.example.testeurosport.model.remote

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.testeurosport.EurosportApplication
import okhttp3.*
import okhttp3.CacheControl
import java.io.File
import java.util.concurrent.TimeUnit

object CacheManager {

    private const val CACHE_SIZE: Long = 50 * 1024 * 1024 // 50MB
    private const val HEADER_PRAGMA = "Pragma"
    private const val HEADER_CACHE_CONTROL = "Cache-Control"

    private fun isNetworkConnected(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run { cm.getNetworkCapabilities(cm.activeNetwork)?.run { result = true } }
        } else {
            cm?.run { cm.activeNetworkInfo?.run { result = true } }
        }

        return result
    }

    private fun getCache(context: EurosportApplication): Cache {
        return Cache(File(context.cacheDir, "http-cache"), CACHE_SIZE)
    }

    private fun getOfflineInterceptor(context: EurosportApplication): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!isNetworkConnected(context)) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private fun getOnlineInterceptor(context: EurosportApplication): Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl: CacheControl = if (isNetworkConnected(context)) {
                CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()
            } else {
                CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
            }
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    fun getOkHttpClient(context: EurosportApplication): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(getOfflineInterceptor(context))
            .addNetworkInterceptor(getOnlineInterceptor(context))
            .cache(getCache(context))
        return httpClient.build()
    }
}