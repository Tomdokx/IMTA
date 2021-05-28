package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class, Hero::class, Record::class, Settings::class], version = 5, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract val playerDAO: PlayerDAO
    abstract val heroDAO: HeroDAO
    abstract val recordDAO: RecordDAO
    abstract val settingsDAO: SettingsDAO

    companion object {

        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GameDatabase::class.java,
                        "database_game"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}