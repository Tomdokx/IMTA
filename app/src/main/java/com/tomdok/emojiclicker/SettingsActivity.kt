package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    private val imgbtn by lazy {
        findViewById<ImageButton>(R.id.imgbtntry)
    }

    private val tVTry by lazy {
        findViewById<TextView>(R.id.tVTry)
    }

    private var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_settings)

        imgbtn.setOnClickListener { inc() }
    }

    private fun inc() {
        x += 1
        tVTry.text = x.toString()
    }
}