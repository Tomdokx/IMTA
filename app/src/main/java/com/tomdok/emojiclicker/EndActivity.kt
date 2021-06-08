package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.DatabaseConfiguration
import database.GameDatabase
import database.Hero
import database.Player
import database.Record
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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

        CoroutineScope(IO).launch {

            GameDatabase.getInstance(applicationContext).heroDAO.deleteAll()

            GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(0, 0, "TestPlayer1"))
            GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(1, 0, "TestPlayer1"))
            GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(2, 0, "TestPlayer1"))
            GameDatabase.getInstance(applicationContext).heroDAO.insert(Hero(3, 0, "TestPlayer1"))

            val player = Player("TestPlayer1",1,0,8.0)
            GameDatabase.getInstance(applicationContext).playerDAO.update(player)

            //GameDatabase.getInstance(applicationContext).recordDAO.insert() ---- ??? need to be done

        }

        buttonGreat.setOnClickListener { finish() }

    }

}