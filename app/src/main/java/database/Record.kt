package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "table_records", foreignKeys = arrayOf(
    ForeignKey(entity = Player::class,
    parentColumns = arrayOf("name"),
    childColumns = arrayOf("player_name"),
    onDelete = ForeignKey.CASCADE))
)
data class Record (

    @PrimaryKey val id: Long? = 0L,
    @ColumnInfo val time: Timestamp,
    @ColumnInfo val level: Int,
    @ColumnInfo(index = true) val player_name: String
)