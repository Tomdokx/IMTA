package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "table_heroes", foreignKeys = arrayOf(ForeignKey(entity = Player::class,
    parentColumns = arrayOf("name"),
    childColumns = arrayOf("player_name"),
    onDelete = ForeignKey.CASCADE))
)
data class Hero (

    @PrimaryKey(autoGenerate = true) val id: Long? = 0L,
    @ColumnInfo val level: Int,
    @ColumnInfo(index = true) val player_name: String
)