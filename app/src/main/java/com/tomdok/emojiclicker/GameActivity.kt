package com.tomdok.emojiclicker

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import database.GameDatabase
import database.Record
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import store.Store
import java.lang.Math.sqrt
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    //TO DO balance heroes / emotes -- LAST THINGY

    //animations of doDMG and switching emotes -- +/-

    // About, settings, xxx

    //Do some graphics such as player + heroes icons, icon for the game and background (can use Biome class for that) -- Pepek thingy

    private val imageViewBoss by lazy {
        findViewById<ImageView>(R.id.game_imageViewBoss)
    }

    private val buttonToShop by lazy {
        findViewById<Button>(R.id.game_buttonShop)
    }

    private val buttonAbility by lazy {
        findViewById<Button>(R.id.game_buttonAbility)
    }

    private val buttonMenu by lazy {
        findViewById<Button>(R.id.game_buttonMenu)
    }

    private val imageButtonEmote by lazy {
        findViewById<ImageButton>(R.id.game_imageButtonEmote)
    }

    private val textViewCoins by lazy {
        findViewById<TextView>(R.id.game_textViewTCoins)
    }

    private val textViewEmoteHP by lazy {
        findViewById<TextView>(R.id.game_textViewEmoteHP)
    }

    private val viewHP by lazy {
        findViewById<View>(R.id.game_viewHPBar)
    }

    private val textViewLevel by lazy {
        findViewById<TextView>(R.id.game_textViewLevel)
    }

    private var store : Store = Store.getInstance()

    private val rnd = Random(8654231597)

    private var player: Player = Store.getInstance().player
    private var emoteList: EmoteLinkedList = EmoteLinkedList()
    private var heroList = Store.getInstance().heroes

    private var widthHPBar = 0

    private var startTime: Long? = null
    private var endTime: Long? = null

    var heroesDoingDmg = mutableListOf<Job?>(null, null, null, null)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_game)

        showTCoins()

        buttonMenu.setOnClickListener { goToMenuActivity() }
        buttonToShop.setOnClickListener { goToShopActivity() }
        buttonAbility.setOnClickListener { doAbility() }

        imageButtonEmote.setOnClickListener { doDpsClick() }

        emoteList.addAll(
            listOf<Emote>(
                Emote(1, "monkaS", 20.0, R.drawable.emote_monkas),
                Emote(2, "pepeLaugh", 50.0, R.drawable.emote_pepelaugh),
                Emote(3, "NOIDONTTHINKSO", 90.5, R.drawable.emote_noidontthinkso),
                Emote(4, "Ddoubledot", 125.0, R.drawable.emote_ddvojtecka),
                Emote(5, "coronaS", 170.5, R.drawable.emote_coronas),
                Emote(6, "ABDULpls", 200.5, R.drawable.emote_abdulpls),
                Emote(7, "ddHuh", 240.5, R.drawable.emote_ddhuh),
                Emote(8, "peepoHappy", 240.5, R.drawable.emote_peepohappy),
                Emote(9, "WaitWhat", 250.0, R.drawable.emote_waitwhat),
                Emote(10, "Sadge", 270.5, R.drawable.emote_sadge),
                Emote(11, "PogTasty", 285.0, R.drawable.emote_pogtasty),
                Emote(12, "3Head", 285.5, R.drawable.emote_3head),
                Emote(13, "AYAYA", 290.5, R.drawable.emote_ayaya),
                Emote(14, "peepoLove", 300.0, R.drawable.emote_peepolove),
                Emote(15, "KEKLEO", 326.5, R.drawable.emote_kekleo),
                Emote(16, "5Head", 340.5, R.drawable.emote_5head),
                Emote(17, "FeelsGoodMan", 350.0, R.drawable.emote_feelsgoodman),
                Emote(18, "gachiGASM", 355.5, R.drawable.emote_gachigasm),
                Emote(19, "hoSway", 370.5, R.drawable.emote_hosway),
                Emote(20, "PauseChamp", 400.0, R.drawable.emote_pausechamp),
                Emote(21, "peepoPooPoo", 450.0, R.drawable.emote_peepopoopoo),
                Emote(22, "forsenCD", 455.5, R.drawable.emote_forsencd),
                Emote(23, "HAHAA", 500.0, R.drawable.emote_hahaa2),
                Emote(24, "peepoSIMP", 510.0, R.drawable.emote_peeposimp),
                Emote(25, "TriKool", 520.5, R.drawable.emote_trikool),
                Emote(26, "weSmart", 520.5, R.drawable.emote_wesmart),
                Emote(27, "LULW", 580.0, R.drawable.emote_lulw),
                Emote(28, "WeirdChamp", 600.0, R.drawable.emote_weirdchamp),
                Emote(29, "MLADY", 650.0, R.drawable.emote_mlady),
                Emote(30, "peepoHey", 700.0, R.drawable.emote_peepohey),
                Emote(31, "BASED", 750.0, R.drawable.emote_based),
                Emote(32, "chikaYo", 800.0, R.drawable.emote_chikayo),
                Emote(33, "COPIUM", 999.0, R.drawable.emote_copium),
                Emote(34, "CouldYouNot", 1050.0, R.drawable.emote_couldyounot),
                Emote(35, "FeelsHangMan", 1200.0, R.drawable.emote_feelshangman),
                Emote(36, "HAhaa", 1250.0, R.drawable.emote_hahaa1),
                Emote(37, "KKomrade", 1300.0, R.drawable.emote_kkomrade),
                Emote(38, "mericCat", 1350.0, R.drawable.emote_mericcat),
                Emote(39, "MonkaHmm", 1400.0, R.drawable.emote_monkahmm),
                Emote(40, "OMEGALUL", 1420.0, R.drawable.emote_omegalul),
                Emote(41, "PagMan", 1470.0, R.drawable.emote_pagman),
                Emote(42, "peepoBlonket", 1520.0, R.drawable.emote_peepoblonket),
                Emote(43, "peepoFAT", 1600.0, R.drawable.emote_peepofat),
                Emote(44, "peepoSad", 1650.0, R.drawable.emote_peeposad),
                Emote(45, "peepoTub", 1700.0, R.drawable.emote_peepotub),
                Emote(46, "pepeL", 1750.0, R.drawable.emote_pepel),
                Emote(47, "pepoClown", 1800.0, R.drawable.emote_pepoclown),
                Emote(48, "pikaO", 1850.0, R.drawable.emote_pikao),
                Emote(49, "PogU", 1900.0, R.drawable.emote_pogu),
                Emote(50, "YEPP", 1950.0, R.drawable.emote_yepp),
                Emote(51, "BOSS KEKW", 1250.69, R.drawable.emote_kekw)
            )
        )

        emoteList.rewind()

        for (i in 0 until store.currentGameLevel) {

            emoteList.next()
        }

        imageButtonEmote.setImageResource(emoteList.actual().picture)
        textViewEmoteHP.text = ((emoteList.actual().currentHp / emoteList.actual().maxHp) * 100).toInt().toString() + "%"
        widthHPBar = viewHP.layoutParams.width
        textViewLevel.text = store.currentGameLevel.toString()

        imageViewBoss.setImageResource(R.drawable.emote_kekw)

        showTCoins()

        doHeroDmg()
    }

    private fun doHeroDmg() {

        heroesDoingDmg[0] = GlobalScope.launch {

            while (true) {

                runBlocking {

                    if(heroList[0].level > 0 && emoteList.hasNext()) {

                        heroList[0].doDamage(emoteList.actual())
                        player.tCoins += addCoins()
                        refreshHPBar()
                    }
                }

                delay(4500)
            }
        }


        heroesDoingDmg[1] = GlobalScope.launch {

            while (true) {

                runBlocking {

                    if(heroList[1].level > 0){

                        heroList[1].doDamage(emoteList.actual())
                        player.tCoins += addCoins()
                        refreshHPBar()
                    }
                }

                delay(5150)
            }
        }

        heroesDoingDmg[2] = GlobalScope.launch {

            while (true) {

                runBlocking {

                    if(heroList[2].level > 0) {

                        heroList[2].doDamage(emoteList.actual())
                        player.tCoins += addCoins()
                        refreshHPBar()
                    }
                }

                delay(7010)
            }
        }

        heroesDoingDmg[3] = GlobalScope.launch {

            while (true) {

                runBlocking {

                    if(heroList[3].level > 0) {

                        heroList[3].doDamage(emoteList.actual())
                        player.tCoins += addCoins()
                        refreshHPBar()
                    }
                }

                delay(9111)
            }
        }
    }

    private fun addCoins(): Int {

        return rnd.nextInt(2*(sqrt(store.currentGameLevel.toDouble()).toInt()),6*(sqrt(store.currentGameLevel.toDouble()).toInt()))
    }

    private fun refreshHPBar() {

        runOnUiThread(Runnable {

            if (emoteList.actual().currentHp < 0) {

                changeEmote()
            }

            textViewEmoteHP.text = ((emoteList.actual().currentHp / emoteList.actual().maxHp) * 100).toInt().toString() + "%"

            showTCoins()

            var ratio = emoteList.actual().currentHp / emoteList.actual().maxHp
            if (ratio <= 0.0) {

                ratio = 0.01
            }

            viewHP.layoutParams.width = (widthHPBar * ratio).toInt()
        })
    }

    private fun doDpsClick() {

        if (startTime == null) {

            startTime = System.currentTimeMillis()
        }

        GlobalScope.launch {

            runBlocking {

                player.clickAndDoDps(emoteList.actual())

                player.tCoins += addCoins()
                refreshHPBar()
            }
        }
    }

    private fun showTCoins() {

        textViewCoins.text = tCoinsToString(player.tCoins)
    }

    private fun changeEmote() {

        if (!emoteList.hasNext()) {

            endTheGame()
        } else {
            store.currentGameLevel += 1
            emoteList.next()
            imageButtonEmote.setImageResource(emoteList.actual().picture)
            textViewLevel.text = store.currentGameLevel.toString()

            if (emoteList.actual().name == "BOSS KEKW") {
                imageViewBoss.setImageResource(R.drawable.emote_kekwait)
            }
        }
    }

    private fun endTheGame() {

        stopHeroCoroutines()

        endTime = System.currentTimeMillis()
        var deltaTime: Long = endTime!! - startTime!!

        runBlocking {

            CoroutineScope(IO).launch {

                GameDatabase.getInstance(applicationContext).recordDAO.insert(

                    Record(null, deltaTime, store.currentGameLevel, player.name)
                )
            }.join()
        }

        val intent = Intent(this, EndActivity::class.java)
        startActivity(intent)

        finish()
    }

    private fun goToShopActivity() {

        stopHeroCoroutines()

        val intent = Intent(this,ShopActivity::class.java)

        startActivity(intent)
    }

    override fun onResume() {

        super.onResume()

        doHeroDmg()
    }

    private fun stopHeroCoroutines() {

        for(heroDoingDmg in heroesDoingDmg){

            heroDoingDmg?.cancel()
        }
    }

    private fun goToMenuActivity() {

        stopHeroCoroutines()

        finish()
    }

    private fun doAbility() {

        buttonAbility.isClickable = false

        GlobalScope.launch {

            GlobalScope.launch {

                var maxLevelIndex = 0

                for (i in heroList.indices) {

                    if (maxLevelIndex < heroList[i].level) {

                        maxLevelIndex = i
                    }
                }

                heroList[maxLevelIndex].doAbility(player, emoteList.actual(), refreshHPBar = {refreshHPBar()})

                refreshHPBar()

            }.join()
            delay(30000)
            runOnUiThread(Runnable {

                buttonAbility.isClickable = true
            })

        }
    }
}