package com.example.cardinfoapp.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(card: CardItemRoom)

    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardItemRoom>>

    @Query("SELECT * from cards WHERE id = :id")
    fun getCard(id: Int): Flow<CardItemRoom>

}