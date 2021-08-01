package com.mikkipastel.soundboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikkipastel.soundboard.dao.SoundPadDao
import com.mikkipastel.soundboard.model.SaveSoundPad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SoundPadViewModel(
    private val dao: SoundPadDao
): ViewModel() {

    val soundPadList = MutableLiveData<MutableList<SaveSoundPad>>()
    val soundPadUpdate = MutableLiveData<SaveSoundPad>()

    fun getSoundPad() = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            dao.getSoundPad()
        }
        soundPadList.value = response
    }

    private fun insertSoundPad(
        saveSoundPad: SaveSoundPad
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            dao.insertSoundPad(saveSoundPad)
        }
    }

    fun updateSoundPad(
        saveSoundPad: SaveSoundPad
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            dao.updatePad(saveSoundPad)
        }
        soundPadUpdate.value = saveSoundPad
    }

    fun initDatabase() = viewModelScope.launch {
        for (i in 0..8) {
            val pad = SaveSoundPad(
                i,
                null
            )
            insertSoundPad(pad)
        }

        val response = withContext(Dispatchers.IO) {
            dao.getSoundPad()
        }
        soundPadList.value = response
    }
}