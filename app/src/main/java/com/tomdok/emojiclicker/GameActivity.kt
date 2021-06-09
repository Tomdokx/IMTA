package com.tomdok.emojiclicker

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
import database.Record
import kotlinx.coroutines.*
import store.Store
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    //TO DO balance heroes / emotes -- LAST THINGY
    //animations of doDMG and switching emotes -- +/-
    //Ability?? New class or 2 new attributes in Hero? -- ???

    //Finally do some graphics such as player + heroes icons, icon for the game and background (can use Biome class for that) -- Pepek thingy

    //Price upgrade + not able to buy upgrade without the amount of TCoins --  +/- DONE
    //Need to handle info about upgrade price

    //After switching activity, heroes still do dmg? -- NO
    //TO DO add Record into the database in End Activity

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

    private var player: Player = Player("xx",0,0,22.2)
    private var emoteList = listOf<Emote>()
    private var heroList = Store.getInstance().heroes

    private var widthHPBar = 0
    private var finished = false

    private var startTime: Long? = null
    private var endTime: Long? = null

    var heroesDoingDmg = mutableListOf<Job?>(null,null,null,null)

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

        imageButtonEmote.setImageResource(emoteList[store.currentGameLevel].picture)
        textViewEmoteHP.text = ((emoteList[store.currentGameLevel].currentHp / emoteList[store.currentGameLevel].maxHp) * 100).toInt().toString() + "%"
        widthHPBar = viewHP.layoutParams.width
        textViewLevel.text = store.currentGameLevel.toString()

        imageViewBoss.setImageResource(R.drawable.emote_kekw)

        showTCoins()

        doHeroDmg()
    }

    private fun doHeroDmg() {

        if (startTime == null) {

            startTime = System.currentTimeMillis()
        }

        heroesDoingDmg[0] = GlobalScope.launch {

            while (!finished) {

                runBlocking {

                    if(heroList[0].level > 0) {

                        heroList[0].doDamage(emoteList[store.currentGameLevel])
                        store.player.tCoins += rnd.nextInt(2, 5)
                        refreshHPBar()
                        if (store.currentGameLevel == emoteList.size -1){

                            finished = true
                            endTheGame()
                        }
                    }
                }

                delay(4500)
            }
        }


        heroesDoingDmg[1] = GlobalScope.launch {

            while (!finished) {

                runBlocking {

                    if(heroList[1].level > 0){

                        heroList[1].doDamage(emoteList[store.currentGameLevel])
                        store.player.tCoins += rnd.nextInt(2,5)
                        refreshHPBar()

                        if (store.currentGameLevel == emoteList.size -1){

                            finished = true
                            endTheGame()
                        }
                    }
                }

                delay(5150)
            }
        }

        heroesDoingDmg[2] = GlobalScope.launch {

            while (!finished) {

                runBlocking {

                    if(heroList[2].level > 0) {

                        heroList[2].doDamage(emoteList[store.currentGameLevel])
                        store.player.tCoins += rnd.nextInt(2, 5)
                        refreshHPBar()

                        if (store.currentGameLevel == emoteList.size -1){

                            finished = true
                            endTheGame()
                        }
                    }
                }

                delay(7010)
            }
        }

        heroesDoingDmg[3] = GlobalScope.launch {

            while (!finished) {

                runBlocking {

                    if(heroList[3].level > 0) {

                        heroList[3].doDamage(emoteList[store.currentGameLevel])
                        store.player.tCoins += rnd.nextInt(2, 5)
                        refreshHPBar()

                        if (store.currentGameLevel == emoteList.size -1){

                            finished = true
                            endTheGame()
                        }
                    }
                }

                delay(9111)
            }
        }
    }

    private fun refreshHPBar() {

        runOnUiThread(Runnable {

            if (emoteList[store.currentGameLevel].currentHp < 0 && store.currentGameLevel < emoteList.size -1) {

                changeEmote()
            }

            textViewEmoteHP.text = ((emoteList[store.currentGameLevel].currentHp / emoteList[store.currentGameLevel].maxHp) * 100).toInt().toString() + "%"

            store.player.tCoins += rnd.nextInt(2,5)

            showTCoins()

            var ratio = emoteList[store.currentGameLevel].currentHp / emoteList[store.currentGameLevel].maxHp
            if (ratio <= 0.0) {

                ratio = 0.01
            }

            viewHP.layoutParams.width = (widthHPBar * ratio).toInt()
        })
    }

    private fun doDpsClick() {

        player.clickAndDoDps(emoteList[store.currentGameLevel])

        GlobalScope.launch {

            runBlocking {

                refreshHPBar()

                if (store.currentGameLevel == emoteList.size -1){

                    finished = true
                    endTheGame()
                }
            }
        }
    }

    private fun showTCoins() {

        var tCoinsShow: String = ""
        var tCoins = store.player.tCoins

        if (tCoins/1000 >= 1) {

            if (tCoins > Int.MAX_VALUE) {

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

    private fun changeEmote() {

        store.currentGameLevel += 1
        imageButtonEmote.setImageResource(emoteList[store.currentGameLevel].picture)
        textViewLevel.text = store.currentGameLevel.toString()
    }

    private fun endTheGame() {

        endTime = System.currentTimeMillis()
        var deltaTime: Long = endTime!! - startTime!!

        GameDatabase.getInstance(applicationContext).recordDAO.insert(
            Record(null, deltaTime, store.currentGameLevel, player.name)
        )

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

        TODO("Not yet implemented")
    }
}