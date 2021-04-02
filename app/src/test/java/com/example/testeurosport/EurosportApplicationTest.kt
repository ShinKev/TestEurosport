package com.example.testeurosport

import android.util.Log
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.BeforeTest
import kotlin.test.Test

class EurosportApplicationTest : KoinTest {

    private val eurosportApplication = EurosportApplication()

    @Test
    fun `check MVVM hierarchy`() {
        checkModules {
            modules(eurosportApplication.applicationModule)
        }
    }
}