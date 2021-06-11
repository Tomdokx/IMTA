package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {


    private val buttonBack by lazy {
        findViewById<Button>(R.id.settings_buttonBack)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_settings)

        buttonBack.setOnClickListener { goBackToMenu() }
    }

    private fun goBackToMenu() {

        finish()
    }
}