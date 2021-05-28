package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HeroDAO {

    @Insert
    fun insert(hero: Hero)

    @Update
    fun update(hero: Hero)

    @Query("SELECT * FROM table_heroes")
    fun getAll(): List<Hero>

    @Query("SELECT * FROM table_heroes WHERE player_name LIKE :player_name")
    fun get(player_name: String): List<Hero>

    @Query("DELETE FROM table_heroes")
    fun deleteAll()

    @Query("DELETE FROM table_heroes WHERE player_name LIKE :player_name")
    fun delete(player_name: String)
}