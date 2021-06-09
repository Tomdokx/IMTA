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
import store.Store

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

    var player: Player = Store.getInstance().player
    var heroes = Store.getInstance().heroes

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_shop)

        buttonBack.setOnClickListener { goBackToGame() }
        buttonUpgrade.isClickable = false

        recyclerView.adapter = ShopRecyclerViewAdapter(applicationContext, heroes, player,
            onClick = { holder, selectedPosition ->

                for (i in 1 until recyclerView.adapter?.itemCount!!) {

                    val holder = recyclerView.findViewHolderForAdapterPosition(i) as ShopRecyclerViewAdapter.ViewHolderHero?
                    holder?.selected = false
                }

                showCalculatedPrice(selectedPosition)

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

        showTCoins()
    }

    private fun showCalculatedPrice(selectedPosition: Int) {

        when(selectedPosition) {

            0-> {

                buttonUpgrade.text = "Upgrade (${tCoinsToString(player.calculatePrice())})"
            }
            else-> {

                buttonUpgrade.text = "Upgrade (${tCoinsToString(heroes[selectedPosition-1].calculatePrice())})"
            }
        }
    }

    private fun upgrade(selectedPosition: Int, holder: RecyclerView.ViewHolder) {

        if (heroes.isNotEmpty()) {

            when (selectedPosition) {

                0 -> {

                    if (player.tCoins < player.calculatePrice()) {

                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderPlayer
                    player.level += 1
                    player.dps += 5.0
                    player.tCoins -= player.calculatePrice()
                    holder.textViewLevel.text = player.level.toString()
                }
                else -> {

                    if (player.tCoins < heroes[selectedPosition-1].calculatePrice()) {

                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderHero
                    heroes[selectedPosition - 1].level += 1
                    holder.textViewLevel.text = heroes[selectedPosition - 1].level.toString()
                    player.tCoins -= heroes[selectedPosition-1].calculatePrice()
                }
            }
        }

        showTCoins()
    }

    private fun goBackToGame() {

        finish()
    }

    private fun showTCoins() {

        textViewCoins.text = tCoinsToString(player.tCoins)
    }
}
