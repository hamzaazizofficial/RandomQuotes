package com.example.randomquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.randomquotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var apiViewModel: ApiViewModel
    private var quote: String = ""
    private var author: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiViewModel = ViewModelProvider(this)[ApiViewModel::class.java]

        showQuote()
        binding.nxtBtn.setOnClickListener {
            showQuote()
        }
        binding.shareBtn.setOnClickListener {
            shareQuote()
        }
    }

    private fun shareQuote() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, "Check out this quote!\n $quote \n said by $author")
        intent.type = "text/plain"
        val shareIntent = Intent.createChooser(intent, "Share this Quote")
        startActivity(shareIntent)
    }

    private fun showQuote() {
        apiViewModel.loadQuote(onSuccess = { response ->
            binding.progressBar.visibility = View.GONE
            quote = response.getString("content")
            author = response.getString("author")
            binding.quoteText.text = quote
            binding.quoteAuthor.text = author

        })

    }
}