package com.example.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.db.converter.StringListConverter
import com.example.db.dao.CharactersDao
import com.example.db.model.CharacterEntity
import com.example.db.model.NextPageUrlEntity

@Database(entities = [CharacterEntity::class, NextPageUrlEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    companion object {

        private const val BD_NAME = "character_database"

        @Volatile
        private var instance: CharactersDatabase? = null

        fun getInstance(application: Application): CharactersDatabase {
            instance?.let { return it }

            return synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context = application.applicationContext,
                    klass = CharactersDatabase::class.java,
                    name = BD_NAME
                ).build().also { instance = it }
            }
        }
    }

    abstract val charactersDao: CharactersDao
}