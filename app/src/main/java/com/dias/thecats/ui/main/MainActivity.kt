package com.dias.thecats.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dias.thecats.databinding.ActivityMainBinding
import com.dias.thecats.ui.CatAdapter

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelProvider()
    }

    private val swipeRefreshLayout by lazy {
        binding.srlMain
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadImages()
        val adapter = CatAdapter()
        binding.rvMain.adapter = adapter
        viewModel.catImages.observe(this) {
            Log.d("MainActivity", "$it")
            adapter.submitList(it)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadImages()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}