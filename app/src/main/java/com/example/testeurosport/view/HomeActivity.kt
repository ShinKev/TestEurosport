package com.example.testeurosport.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testeurosport.R
import com.example.testeurosport.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}