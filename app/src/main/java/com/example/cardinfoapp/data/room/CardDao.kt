package com.example.cardinfoapp.data.room
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

    @Query("SELECT * FROM cards WHERE id=:id")
    suspend fun getCard(id: Int): CardItemRoom

}