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
        Store.getInstance().player.tCoins = 0
        Store.getInstance().player.level = 1

        for (hero in Store.getInstance().heroes){
            hero.level = 0
        }

        buttonGreat.setOnClickListener { finish() }
    }
}