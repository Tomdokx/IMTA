package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDAO {

    @Insert
    fun insert(record: Record)

    @Query("SELECT * FROM table_records")
    fun getAll(): List<Record>

    @Query("SELECT * FROM table_records WHERE player_name LIKE :player_name")
    fun get(player_name: String): Record

    @Query("DELETE FROM table_records")
    fun deleteAll()

    @Query("DELETE FROM table_records WHERE player_name LIKE :player_name")
    fun delete(player_name: String)
}