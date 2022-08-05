package com.dias.thecats.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dias.thecats.databinding.ActivityMainBinding
import com.dias.thecats.ui.CatAdapter
import com.dias.thecats.ui.CatViewModel
import com.dias.thecats.ui.detail.DetailActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val viewModel: CatViewModel by viewModels {
        CatViewModel.Provider(this)
    }

    private val swipeRefreshLayout by lazy { binding.root }

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
            adapter.refresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}