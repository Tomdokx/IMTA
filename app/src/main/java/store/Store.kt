package store

import com.tomdok.emojiclicker.R
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import java.sql.Struct

class Store {

    var currentGameLevel: Int
    var player: Player
    var heroes = emptyList<Hero>()

    init {

        currentGameLevel = 1
        player = Player("TestPlayer1", 1, 0, 8.0)
        heroes += Hero("Hero1", 0, 5.0, R.drawable.avatar2)
        heroes += Hero("Hero2", 0, 10.0, R.drawable.avatar2)
        heroes += Hero("Hero3", 0, 15.0, R.drawable.avatar2)
        heroes += Hero("Hero4", 0, 20.0, R.drawable.avatar2)
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
