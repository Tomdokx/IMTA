package com.tomdok.emojiclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    //TO-DO rename val of components

    private val imageViewBoss by lazy {
        findViewById<ImageView>(R.id.iVBoss)
    }

    private val buttonToShop by lazy {
        findViewById<Button>(R.id.btnShop)
    }

    private val buttonAbility by lazy {
        findViewById<Button>(R.id.btnAbility)
    }

    private val buttonMenu by lazy {
        findViewById<Button>(R.id.btnMenu)
    }

    private val imageButtonEmote by lazy {
        findViewById<ImageButton>(R.id.ibtnMonster)
    }

    private val tVCoins by lazy {
        findViewById<TextView>(R.id.tvTCoinsGame)
    }

    private val tVEmoteHP by lazy {
        findViewById<TextView>(R.id.tVEmoteHP)
    }

    private val viewHP by lazy {
        findViewById<View>(R.id.viewHPBar)
    }

    private val textViewLevel by lazy {
        findViewById<TextView>(R.id.tvLevel)
    }

    private var tCoins = 0

    private val rnd = Random(8654231597)

    private var currentIdEmote = 1

    private var player: Player = Player("xx",0,0,22.2)
    private var emoteList = listOf<Emote>()
    private var heroList = listOf<Hero>()

    private var widthHPBar = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_game)

        loadData()

        buttonMenu.setOnClickListener { goToMenuActivity() }
        buttonToShop.setOnClickListener { goToShopActivity() }
        buttonAbility.setOnClickListener { doAbility() }

        imageButtonEmote.setOnClickListener { doDpsClick() }

        emoteList = listOf<Emote>(
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
            Emote(23, "HAHAA", 500.0, R.drawable.emote_hahaa),
            Emote(24, "peepoSIMP", 510.0, R.drawable.emote_peeposimp),
            Emote(25, "TriKool", 520.5, R.drawable.emote_trikool),
            Emote(26, "weSmart", 520.5, R.drawable.emote_wesmart),
            Emote(27, "LULW", 580.0, R.drawable.emote_lulw),
            Emote(28, "WeirdChamp", 600.0, R.drawable.emote_weirdchamp),
            Emote(29, "MLADY", 650.0, R.drawable.emote_mlady),
            Emote(30, "peepoHey", 700.0, R.drawable.emote_peepohey),
            Emote(31, "BOSS KEKW", 1250.69, R.drawable.emote_kekw)
        )

        imageButtonEmote.setImageResource(emoteList[currentIdEmote].picture)
        tVEmoteHP.text = ((emoteList[currentIdEmote].currentHp / emoteList[currentIdEmote].maxHp) * 100).toInt().toString() + "%"
        widthHPBar = viewHP.layoutParams.width
        textViewLevel.text = currentIdEmote.toString()

        imageViewBoss.setImageResource(R.drawable.emote_kekw)

        showTCoins()
    }

    private fun loadData() {
        var optionalPlayer: Player? = null

        runBlocking {

            CoroutineScope(IO).launch {

                val databasePlayer: database.Player? = GameDatabase.getInstance(applicationContext).playerDAO.get("TestPlayer1")


                databasePlayer?.let { databasePlayer ->

                    optionalPlayer = Player(databasePlayer.name, databasePlayer.level, databasePlayer.coins, databasePlayer.dps)
                    val databaseHeroes: List<database.Hero> = GameDatabase.getInstance(applicationContext).heroDAO.get("TestPlayer1")
                    heroList += Hero(databaseHeroes[0].id!!, "Hero1", databaseHeroes[0].level, 2.0, R.drawable.avatar2)
                    heroList += Hero(databaseHeroes[1].id!!, "Hero2", databaseHeroes[1].level, 3.0, R.drawable.avatar2)
                    heroList += Hero(databaseHeroes[2].id!!, "Hero3", databaseHeroes[2].level, 5.0, R.drawable.avatar2)
                    heroList += Hero(databaseHeroes[3].id!!, "Hero4", databaseHeroes[3].level, 8.0, R.drawable.avatar2)
                }
            }.join()
        }

        if (optionalPlayer != null) {

            player = optionalPlayer!!
            tCoins = player.tCoins
        }

        showTCoins()
    }

    private fun doDpsClick() {

    player.clickAndDoDps(emoteList[currentIdEmote])

    if (emoteList[currentIdEmote].currentHp < 0){

        changeEmote()
    }

        tVEmoteHP.text = ((emoteList[currentIdEmote].currentHp / emoteList[currentIdEmote].maxHp) * 100).toInt().toString() + "%"

    tCoins += rnd.nextInt(2,5)

        showTCoins()

        var ratio = emoteList[currentIdEmote].currentHp / emoteList[currentIdEmote].maxHp
        if (ratio <= 0.0){

            ratio = 0.01
        }
        viewHP.layoutParams.width = (widthHPBar * ratio).toInt()
    }

    private fun showTCoins() {

        var tCoinsShow: String = ""

        if (tCoins/1000 >= 1) {

            if (tCoins > Int.MAX_VALUE){

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

        tVCoins.text = tCoinsShow
    }

    private fun changeEmote() {

        currentIdEmote += 1
        imageButtonEmote.setImageResource(emoteList[currentIdEmote].picture)
        textViewLevel.text = currentIdEmote.toString()
    }

    private fun goToShopActivity() {

        saveData()

        val intent = Intent(this,ShopActivity::class.java)

        startActivity(intent)
    }

    override fun onResume() {

        super.onResume()
        loadData()
    }

    private fun saveData() {
        player.tCoins = tCoins

        runBlocking {

            CoroutineScope(IO).launch {

                val playerUpdate = database.Player(player.name,player.level,player.tCoins,player.dps)
                GameDatabase.getInstance(applicationContext).playerDAO.update(playerUpdate)
            }.join()
        }
    }

    private fun goToMenuActivity() {

        saveData()

        finish()
    }

    private fun doAbility() {

        TODO("Not yet implemented")
    }
}