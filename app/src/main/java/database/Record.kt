package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "table_records")
data class Record (

    @PrimaryKey(autoGenerate = true) val id: Long? = 0L,
    @ColumnInfo(name = "time") val time: Float,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(index = true) val player_name: String
)