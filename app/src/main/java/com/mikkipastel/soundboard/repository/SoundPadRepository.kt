package com.mikkipastel.soundboard.repository

import com.mikkipastel.soundboard.dao.SoundPadDao
import com.mikkipastel.soundboard.model.SaveSoundPad

interface SoundPadRepository {
    suspend fun getSoundPad(): MutableList<SaveSoundPad>
    suspend fun insertSoundPad(saveSoundPad: SaveSoundPad)
}

class SoundPadRepositoryImpl(
    private val dao: SoundPadDao
): SoundPadRepository {
    override suspend fun getSoundPad(): MutableList<SaveSoundPad> {
        return dao.getSoundPad()
    }

    override suspend fun insertSoundPad(saveSoundPad: SaveSoundPad) {
        dao.insertSoundPad(saveSoundPad)
    }
}