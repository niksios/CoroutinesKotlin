package com.niks.coroutineskotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var count = 0

    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCount = findViewById<TextView>(R.id.tvCount)
        tvMessage = findViewById(R.id.tvMessage)
        val btndownload = findViewById<Button>(R.id.btnDownload)
        val btnCount = findViewById<Button>(R.id.btnCount)

        btndownload.setOnClickListener {
            //create a Coroutine with Dispatcher.IO
           CoroutineScope(Dispatchers.IO).launch {
               downloadUserData()
           }
        }

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

    }

    private suspend fun downloadUserData(){
        for (i in 1..200000){
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            //this is a suspend function used in coroutines where we mark the function with suspend modifier
            withContext(Dispatchers.Main){
                tvMessage.text = "Downloading user $i"
            }

            delay(100)
        }
    }


}