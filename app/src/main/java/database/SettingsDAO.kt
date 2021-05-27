package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SettingsDAO {

    @Insert
    fun insertSettings(settings: Settings)

    @Delete
    fun deleteSettings()

    @Query("DELETE FROM table_settings WHERE player_name LIKE :player_name")
    fun deleteSettings(player_name: String)

    @Query("SELECT * FROM table_settings")
    fun getAll()

    @Query("SELECT * FROM table_settings WHERE player_name LIKE :player_name")
    fun getAll(player_name: String)
}