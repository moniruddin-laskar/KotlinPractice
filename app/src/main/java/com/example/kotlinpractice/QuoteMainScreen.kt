package com.example.kotlinpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class QuoteMainScreen : AppCompatActivity() {

    lateinit var quoteViewModel: QuoteViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    val totalQuotes = quoteViewModel.quoteCount
    val currentIndex = quoteViewModel.index

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_main_screen)

        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModelFactory(application)
        ).get(QuoteViewModel::class.java)

        setQuote(quoteViewModel.getQuote())
    }

    fun setQuote(quote: Quote) {
        quoteText.text = quote.quote
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {

        if (currentIndex >= 0){
            setQuote(quoteViewModel.previousQuote())
        }else{
            Toast.makeText(this, "You are in first quote", Toast.LENGTH_SHORT).show()
        }
    }

    fun onNext(view: View) {
        if (totalQuotes > currentIndex){
            setQuote(quoteViewModel.nextQuote())
        }else{
            Toast.makeText(this, "You are in last quote", Toast.LENGTH_SHORT).show()
        }
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.setType("text/plain")

        intent.putExtra(
            Intent.EXTRA_TEXT, quoteViewModel.getQuote().quote
        )
        startActivity(intent)
    }
}