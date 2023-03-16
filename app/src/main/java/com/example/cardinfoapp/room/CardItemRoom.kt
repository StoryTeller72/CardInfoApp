package com.example.cardinfoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardItemRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "length")
    val length: String,
    @ColumnInfo(name = "luhn")
    val luhn: String,
    @ColumnInfo(name = "sheme")
    val sheme: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "prepaid")
    val prepaid: String,
    @ColumnInfo(name = "numeric")
    val numeric: String,
    @ColumnInfo(name = "alpha2")
    val alpha2: String,
    @ColumnInfo(name = "countryName")
    val countryName:String,
    @ColumnInfo(name = "emoji")
    val emoji:String,
    @ColumnInfo(name = "currence")
    val currency: String,
    @ColumnInfo(name = "latitude")
    val latitude: Int,
    @ColumnInfo(name = "longitude")
    val longitude:Int,
    @ColumnInfo(name = "bankName")
    val bankName: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "dateRequest")
    val dateRequest: String
)