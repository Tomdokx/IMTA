package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import database.GameDatabase
import database.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import store.Store

class EndActivity : AppCompatActivity() {

    val buttonGreat by lazy {
        findViewById<Button>(R.id.end_buttonGreat)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_end)

        Store.getInstance().currentGameLevel = 1

        buttonGreat.setOnClickListener { finish() }
    }
}