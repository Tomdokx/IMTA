package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import database.GameDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ShopActivity : AppCompatActivity() {

    private val buttonUpgrade by lazy {
        findViewById<Button>(R.id.shop_buttonUpgrade)
    }

    private val buttonBack by lazy {
        findViewById<Button>(R.id.shop_buttonBack)
    }

    private val textViewCoins by lazy {
        findViewById<TextView>(R.id.shop_textViewCoins)
    }

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.shop_recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_shop)

        buttonBack.setOnClickListener { goBackToGame() }
        buttonUpgrade.setOnClickListener { upgrade() }

        var optionalPlayer: Player? = null
        var heroes = listOf<Hero>()

        runBlocking {

            CoroutineScope(IO).launch {

                val databasePlayer: database.Player? = GameDatabase.getInstance(applicationContext).playerDAO.get("TestPlayer1")

                databasePlayer?.let { databasePlayer ->

                    optionalPlayer = Player(databasePlayer.name, databasePlayer.level, databasePlayer.coins, databasePlayer.dps)

                    val databaseHeroes: List<database.Hero> = GameDatabase.getInstance(applicationContext).heroDAO.get("TestPlayer1")
                    heroes += Hero(databaseHeroes[0].id!!, "Hero1", databaseHeroes[0].level, 12.0, R.drawable.avatar2)
                    heroes += Hero(databaseHeroes[1].id!!, "Hero2", databaseHeroes[1].level, 23.0, R.drawable.avatar2)
                    heroes += Hero(databaseHeroes[2].id!!, "Hero3", databaseHeroes[2].level, 45.0, R.drawable.avatar2)
                    heroes += Hero(databaseHeroes[3].id!!, "Hero4", databaseHeroes[3].level, 68.0, R.drawable.avatar2)
                }

            }.join()
        }

        optionalPlayer?.let { player ->

            textViewCoins.text = player.tCoins.toString()

            recyclerView.adapter = ShopRecyclerViewAdapter(applicationContext, heroes, player,
                onClick = { holder, selectedPosition ->

                    for (i in 1 until recyclerView.adapter?.itemCount!!) {

                        val holder = recyclerView.findViewHolderForAdapterPosition(i) as ShopRecyclerViewAdapter.ViewHolderHero?
                        holder?.selected = false
                    }

                    when (selectedPosition) {

                        0 -> {

                            val holder = holder as ShopRecyclerViewAdapter.ViewHolderPlayer
                            holder.selected = true
                        }
                        else -> {

                            val holder = holder as ShopRecyclerViewAdapter.ViewHolderHero
                            holder.selected = true
                            val playerHolder = recyclerView.findViewHolderForAdapterPosition(0) as ShopRecyclerViewAdapter.ViewHolderPlayer?
                            playerHolder?.selected = false
                        }
                    }
                })
        } ?: return
    }

    private fun upgrade() {

        TODO("Not yet implemented")
    }

    private fun goBackToGame() {

        finish()
    }
}
