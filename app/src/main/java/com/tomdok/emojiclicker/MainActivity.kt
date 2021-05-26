package com.tomdok.emojiclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


const val EXTRA_MESSAGE = "com.example.emojiclicker.MESSAGE"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun heroes(view: View){
        val intent = Intent(this,HeroesActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,0)
        }
        startActivity(intent)
    }
}