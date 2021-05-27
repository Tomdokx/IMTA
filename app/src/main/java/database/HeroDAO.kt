package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HeroDAO {

    @Insert
    fun insertHero(hero: Hero)

    @Query("DELETE FROM table_heroes")
    fun deleteAll()

    @Query("DELETE FROM table_heroes WHERE player_name LIKE :player_name")
    fun deleteAll(player_name: String)

    @Update
    fun updateHero(hero: Hero)

    @Query("SELECT * FROM table_heroes")
    fun getAll() : List<Hero>

    @Query("SELECT * FROM table_heroes WHERE player_name LIKE :player_name")
    fun getAll(player_name: String) : List<Hero>
}