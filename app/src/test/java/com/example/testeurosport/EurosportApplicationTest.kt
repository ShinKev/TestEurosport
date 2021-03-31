package com.example.testeurosport

import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

class EurosportApplicationTest : KoinTest {

    private val eurosportApplication: EurosportApplication = EurosportApplication()

    @Test
    fun `check MVVM hierarchy`() {
        checkModules {
            modules(eurosportApplication.applicationModule)
        }
    }
}