package com.dias.thecats.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dias.thecats.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)

        binding.url = intent.extras?.getString(URL_KEY)
        title = ""
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val URL_KEY = "url"
    }
}