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

                buttonUpgrade.setOnClickListener { upgrade(selectedPosition, holder) }
            })

    showTCoins()
}

    private fun upgrade(selectedPosition: Int, holder: RecyclerView.ViewHolder) {

        if (heroes.isNotEmpty()) {

            when (selectedPosition) {

                0 -> {

                    if (player.tCoins < 50) {

                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderPlayer
                    player.level += 1
                    player.dps += 5.0
                    player.tCoins -= 50
                    holder.textViewLevel.text = player.level.toString()
                }

                else -> {

                    if (player.tCoins < 20){
                        buttonUpgrade.isClickable = false
                        return
                    }

                    val holder = holder as ShopRecyclerViewAdapter.ViewHolderHero
                    heroes[selectedPosition - 1].level += 1
                    holder.textViewLevel.text = heroes[selectedPosition - 1].level.toString()
                    player.tCoins -= 20
                }
            }
        }

        showTCoins()
    }


    private fun goBackToGame() {

        finish()
    }

    private fun showTCoins() {

        var tCoinsShow: String = ""
        var tCoins = player.tCoins

        if (tCoins/1000 >= 1) {

            if (tCoins > Double.MAX_VALUE){

                tCoinsShow += "XXX"
            } else if (tCoins / 1000000000 >= 1) {

                tCoinsShow += "%.2f B".format(tCoins / 1000000000.0)
            } else if (tCoins / 1000000 >= 1) {

                tCoinsShow += "%.2f M".format(tCoins / 1000000.0)
            } else {

                tCoinsShow += "%.2f K".format(tCoins / 1000.0)
            }
        } else {

            tCoinsShow += tCoins.toString()
        }

        textViewCoins.text = tCoinsShow
    }
}
