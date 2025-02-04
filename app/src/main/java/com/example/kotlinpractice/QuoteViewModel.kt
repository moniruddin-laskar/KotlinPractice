package com.example.kotlinpractice

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class QuoteViewModel(val context: Context): ViewModel() {

    private  var quoteList: Array<Quote> = emptyArray()

      var index = 0

    val quoteCount: Int
        get() = quoteList.size

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
      val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String( buffer, Charsets.UTF_8)

        val gson = Gson()

        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]

}