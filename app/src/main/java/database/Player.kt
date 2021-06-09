package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_players")
data class Player (

    @PrimaryKey val name: String,
)