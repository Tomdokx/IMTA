package database

import androidx.room.*

@Dao
interface PlayerDAO {

    @Insert
    fun insert(player: Player)

    @Query("SELECT * FROM table_players")
    fun getAll(): List<Player>

    @Query("SELECT * FROM table_players WHERE name LIKE :name")
    fun get(name: String): Player

    @Query("DELETE FROM table_players")
    fun deleteAll()

    @Query("DELETE FROM table_players WHERE name LIKE :name")
    fun delete(name: String)

    @Update
    fun update(player: Player)
}