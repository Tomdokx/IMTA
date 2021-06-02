package com.tomdok.emojiclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InfoActivity : AppCompatActivity() {

    private val btnBack by lazy {
        findViewById<Button>(R.id.btnBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_info)

        btnBack.setOnClickListener { goBack() }
    }

    private fun goBack() {

        finish()
    }
}