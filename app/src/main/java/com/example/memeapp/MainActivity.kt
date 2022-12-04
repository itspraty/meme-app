package com.example.memeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonFront=findViewById<Button>(R.id.buttonFront)
        buttonFront.setOnClickListener{
            val intent=Intent(this,memes::class.java)
            startActivity(intent)
        }
    }
}