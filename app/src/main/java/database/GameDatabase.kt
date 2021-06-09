package database

import android.content.Context
import androidx.room.*

@Database(entities = [Player::class, Record::class, Settings::class], version = 11, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract val playerDAO: PlayerDAO
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