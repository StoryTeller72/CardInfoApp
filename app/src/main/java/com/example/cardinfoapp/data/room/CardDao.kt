package com.example.cardinfoapp.data.room
import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(card: CardItemRoom)

    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardItemRoom>>

    @Query("SELECT * FROM cards WHERE bin=:bin LIMIT 1")
    suspend fun getCard(bin: String): CardItemRoom
}