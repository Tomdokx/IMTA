package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDAO {

    @Insert
    fun insert(settings: Settings)

    @Query("DELETE FROM table_settings")
    fun deleteAll()

    @Query("DELETE FROM table_settings WHERE player_name LIKE :player_name")
    fun delete(player_name: String)

    @Query("SELECT * FROM table_settings")
    fun getAll(): List<Settings>

    @Query("SELECT * FROM table_settings WHERE player_name LIKE :player_name")
    fun get(player_name: String): Settings

    @Update
    fun update(settings: Settings)
}