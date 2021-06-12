package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.rename
import android.widget.*
import database.GameDatabase
import database.Settings
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

        buttonRename.setOnClickListener { rename() }

        switchAnimation?.setOnCheckedChangeListener { _, isChecked->

            runBlocking {

                coroutineScope {

                    launch {

                        val settingsUpdate = Settings(null,isChecked,store.player.name)
                        GameDatabase.getInstance(applicationContext).settingsDAO.update(settingsUpdate)
                    }
                }
            }
        }
    }

    private fun rename() {

        val newName = editTextRename.text.toString()!!
        store.player.name = newName
    }

    private fun goBackToMenu() {

        finish()
    }
}