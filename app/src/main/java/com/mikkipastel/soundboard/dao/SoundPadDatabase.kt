package com.mikkipastel.soundboard.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mikkipastel.soundboard.model.SaveSoundPad

const val SoundPadTable = "soundpad_table"

@Database(entities = [SaveSoundPad::class], version = 1, exportSchema = false)
abstract class SoundPadDatabase: RoomDatabase() {
    abstract val soundPadDao: SoundPadDao

    companion object {
        @Volatile
        private var INSTANCE: SoundPadDatabase? = null

        fun getSoundPadDatabase(context: Context): SoundPadDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SoundPadDatabase::class.java,
                    SoundPadTable
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}