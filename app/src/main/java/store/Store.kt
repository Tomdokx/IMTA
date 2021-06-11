package store

import com.tomdok.emojiclicker.Abilities.AbilityDoExtraDamage
import com.tomdok.emojiclicker.Abilities.AbilityDoubleDmgHero
import com.tomdok.emojiclicker.Abilities.AbilityPowerUpPlayer
import com.tomdok.emojiclicker.Abilities.AbilityQuickDamage
import com.tomdok.emojiclicker.R
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player

class Store {

    var currentGameLevel: Int
    var player: Player
    var heroes = emptyList<Hero>()

    init {

        currentGameLevel = 1
        player = Player("TestPlayer1", 1, 5000, 40.0, 100)
        heroes += Hero("Spartan", 0, 5.0, R.drawable.hero_spartan,20,100, AbilityPowerUpPlayer(1.4,8000))
        heroes += Hero("Viking", 0, 10.0, R.drawable.hero_viking,80, 350, AbilityDoubleDmgHero(7000))
        heroes += Hero("Wizard", 0, 15.0, R.drawable.hero_wizard,200,800, AbilityDoExtraDamage(20.0,10000))
        heroes += Hero("Knight", 0, 20.0, R.drawable.hero_knight,400,1500, AbilityQuickDamage(500.0))
    }

    companion object {

        @Volatile
        private var instance: Store? = null

        fun getInstance(): Store {

            if (instance == null)
                instance = Store()

            return instance as Store
        }
    }
}
