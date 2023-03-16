package com.example.cardinfoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CardItemRoom::class], version = 1)
abstract class CardDatabase: RoomDatabase() {

    abstract fun cardDao():Dao

    companion object{
        @Volatile
        var INSTANCE: CardDatabase? = null

        fun getDatabase(context: Context): CardDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "cardDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}