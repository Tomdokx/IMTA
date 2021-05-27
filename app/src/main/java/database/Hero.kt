package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "table_heroes")
data class Hero (

    @PrimaryKey(autoGenerate = true) val id: Long? = 0L,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(index = true) val player_name: String
)