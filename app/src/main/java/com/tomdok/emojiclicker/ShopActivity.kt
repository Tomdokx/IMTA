package com.tomdok.emojiclicker

import android.app.Activity
import android.content.Intent
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
import android.widget.Toast

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

    private var tCoins = 0

    var optionalPlayer: Player? = null
    var heroes = listOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_shop)

        buttonBack.setOnClickListener { goBackToGame() }

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

            tCoins = player.tCoins

            recyclerView.adapter = ShopRecyclerViewAdapter(applicationContext, heroes, optionalPlayer!!,
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

                    buttonUpgrade.setOnClickListener { upgrade(selectedPosition, holder) }
                })
        } ?: return

        showTCoins()
    }

    private fun upgrade(selectedPosition: Int, holder: RecyclerView.ViewHolder) {

        if (optionalPlayer != null && heroes.isNotEmpty()) {

            when (selectedPosition) {

                0 -> {

                    if (tCoins < 50) {

                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderPlayer
                    optionalPlayer!!.level += 1
                    optionalPlayer!!.dps += 5.0
                    tCoins -= 50
                    holder.textViewLevel.text = optionalPlayer?.level.toString()
                }

                else -> {

                    if (tCoins < 20){
                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderHero
                    heroes[selectedPosition - 1].level += 1
                    holder.textViewLevel.text = heroes[selectedPosition - 1].level.toString()
                    tCoins -= 20
                }
            }
        }

        showTCoins()
    }


    private fun goBackToGame() {

        saveData()

        finish()
    }

    private fun saveData() {
        optionalPlayer!!.tCoins = tCoins

        runBlocking {

            CoroutineScope(IO).launch {

                val playerUpdate = database.Player(optionalPlayer!!.name,optionalPlayer!!.level,optionalPlayer!!.tCoins,optionalPlayer!!.dps)
                GameDatabase.getInstance(applicationContext).playerDAO.update(playerUpdate)

                for(hero in heroes){

                    val heroUpdate = database.Hero(hero.id,hero.level,optionalPlayer!!.name)
                    GameDatabase.getInstance(applicationContext).heroDAO.update(heroUpdate)
                }

            }.join()
        }
    }

    private fun showTCoins() {

        var tCoinsShow: String = ""

        if (tCoins/1000 >= 1) {

            if (tCoins > Double.MAX_VALUE){
                tCoinsShow += "XXX"
            }

            else if (tCoins / 1000000000 >= 1) {

                tCoinsShow += "%.2f B".format(tCoins / 1000000000.0)
            } else if (tCoins / 1000000 >= 1) {

                tCoinsShow += "%.2f M".format(tCoins / 1000000.0)
            } else {

                tCoinsShow += "%.2f K".format(tCoins / 1000.0)
            }
        }else{

            tCoinsShow += tCoins.toString()
        }

        textViewCoins.text = tCoinsShow
    }
}
