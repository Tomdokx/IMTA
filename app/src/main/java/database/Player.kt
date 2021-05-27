package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_players")
data class Player (

    @PrimaryKey(autoGenerate = true) val id: Long? = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "coins") val coins: Int,
    @ColumnInfo(name = "dps") val dps: Float
)