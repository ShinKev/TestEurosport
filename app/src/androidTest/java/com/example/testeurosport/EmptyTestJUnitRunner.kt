package com.example.testeurosport

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class EmptyTestJUnitRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, EmptyTestApplication::class.java.name, context)
    }
}