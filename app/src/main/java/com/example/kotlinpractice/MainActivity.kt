package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

     lateinit var txtCounter : TextView

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)
        txtCounter = this.findViewById(R.id.txtCounter)

    }



    fun setText(){
        txtCounter.text = mainViewModel.count.toString()
    }


    fun increment( V: View){
        mainViewModel.increment()
        setText()
    }


}