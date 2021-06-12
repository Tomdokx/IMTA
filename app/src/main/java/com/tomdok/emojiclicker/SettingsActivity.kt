package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.rename
import android.widget.*
import store.Store

class SettingsActivity : AppCompatActivity() {


    private val buttonBack by lazy {
        findViewById<Button>(R.id.settings_buttonBack)
    }

    private val switchAnimation by lazy {
        findViewById<Switch>(R.id.settings_switchAnimation)
    }

    private val editTextRename by lazy {
        findViewById<EditText>(R.id.settings_textViewRename)
    }

    private val buttonRename by lazy {
        findViewById<Button>(R.id.settings_buttonRename)
    }

    val store = Store()

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