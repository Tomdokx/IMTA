package com.tomdok.emojiclicker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.sql.Timestamp


class MainActivity : AppCompatActivity() {

    private val btnToGame by lazy {
        findViewById<Button>(R.id.btnGame)
    }

    private val btnToRecords by lazy {
        findViewById<Button>(R.id.btnRecords)
    }

    private val btnToInfo by lazy {
        findViewById<Button>(R.id.btnAbout)
    }

    private val btnToSettings by lazy {
        findViewById<Button>(R.id.btnSettings)
    }

    private val btnQuit by lazy {
        findViewById<Button>(R.id.btnQuit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_main)

        btnToGame.setOnClickListener { goToActivity(1) }
        btnToRecords.setOnClickListener { goToActivity(2) }
        btnToInfo.setOnClickListener { goToActivity(3) }
        btnToSettings.setOnClickListener { goToActivity(4) }
        btnQuit.setOnClickListener { quitTheGame() }

        // temporary
        if (false) {

            CoroutineScope(IO).launch {
                GameDatabase.getInstance(applicationContext).playerDAO.insert(Player("TestPlayer1", 56, 12, 15.6))
                GameDatabase.getInstance(applicationContext).playerDAO.insert(Player("TestPlayer2", 56, 12, 15.6))
                GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(0, 1, "TestHero1"))
                GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(1, 0, "TestHero2"))
                GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(2, 0, "TestHero3"))
                GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(3, 0, "TestHero4"))
                GameDatabase.getInstance(applicationContext).settingsDAO.insert(Settings(0, true, "TestPlayer1"))
                val player = Player("TestPlayer1", 54, 1532, 78.6)
                GameDatabase.getInstance(applicationContext).playerDAO.update(player)
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 14, "TestPlayer1"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 12, "TestPlayer2"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 17, "TestPlayer2"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 14, "TestPlayer1"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 56, "TestPlayer1"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 45, "TestPlayer1"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 15, "TestPlayer2"))
                GameDatabase.getInstance(applicationContext).recordDAO.insert(Record(null, Timestamp(System.currentTimeMillis()), 47, "TestPlayer2"))
            }
        }
    }

    private fun quitTheGame() {

        finish()
    }

    override fun onDestroy() {

        //TODO save the record..

        super.onDestroy()
    }

    private fun goToActivity(act: Int) {

        val intentToActivity = Intent()

        when (act) {
            1 -> intentToActivity.setClass(this,GameActivity::class.java)
            2 -> intentToActivity.setClass(this,RecordActivity::class.java)
            3 -> intentToActivity.setClass(this,InfoActivity::class.java)
            4 -> intentToActivity.setClass(this,SettingsActivity::class.java)

            else -> {}
        }

        startActivity(intentToActivity)
    }
}