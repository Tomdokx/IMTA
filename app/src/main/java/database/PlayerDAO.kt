package database

import androidx.room.*

@Dao
interface PlayerDAO {

    @Insert
    fun insertPlayer(player: Player)

    @Delete
    fun deletePlayer(name: String)

    @Query("DELETE FROM table_players")
    fun deleteAll()

    @Update
    fun updatePlayer(player: Player)

    @Query("SELECT * FROM table_players WHERE name LIKE :name")
    fun getPlayer(name: String): Player

    @Query("SELECT * FROM table_players")
    fun getAll(): List<Player>
}