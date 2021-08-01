package com.mikkipastel.soundboard.dao

import androidx.room.*
import com.mikkipastel.soundboard.model.SaveSoundPad

@Dao
interface SoundPadDao {
    @Query("SELECT * FROM $SoundPadTable")
    fun getSoundPad() : MutableList<SaveSoundPad>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSoundPad(soundPad: SaveSoundPad)

    @Update
    fun updatePad(soundPad: SaveSoundPad)

    @Query("DELETE FROM $SoundPadTable")
    fun deleteAllPad()
}