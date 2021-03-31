package com.example.testeurosport

import android.app.Application
import com.example.testeurosport.model.DataRepository
import com.example.testeurosport.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EurosportApplication : Application() {

    val applicationModule = module {
        single { DataRepository() }
        viewModel { HomeViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EurosportApplication)
            modules(applicationModule)
        }
    }
}