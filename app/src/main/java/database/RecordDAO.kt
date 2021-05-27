package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDAO {

    @Insert
    fun insertRecord(record: Record)

    @Query("DELETE FROM table_records")
    fun deleteAll()

    @Query("DELETE FROM table_records WHERE player_name LIKE :player_name")
    fun deleteAll(player_name: String)

    @Query("SELECT * FROM table_records")
    fun getAll() : List<Record>

    @Query("SELECT * FROM table_records WHERE player_name LIKE :player_name")
    fun getAll(player_name: String) : List<Record>
}