package com.nn.tz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nn.tz.data.model.CategoryEntity
import com.nn.tz.data.model.Product

@Database(entities = [CategoryEntity::class, Product::class], version = 2)
@TypeConverters(RatingConverter::class,Converters::class)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao():ProductDao
}