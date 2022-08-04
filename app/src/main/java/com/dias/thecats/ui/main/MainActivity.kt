package com.dias.thecats.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dias.thecats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CatAdapter()
        binding.rvMain.adapter = adapter
        lifecycleScope.launch {
            viewModel.catImagesFlow.collectLatest(adapter::submitData)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadImages()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}