package store

import android.content.Context
import androidx.room.Room
import database.GameDatabase

class Store {

    companion object {

        @Volatile
        private var instance: Store? = null

        fun getInstance(): Store {

            if (instance == null)
                instance = Store()

            return instance as Store
        }
    }

    var currentGameLevel: Int = 1
}
