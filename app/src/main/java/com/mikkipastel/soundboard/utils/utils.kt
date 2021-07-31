package com.mikkipastel.soundboard.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mikkipastel.soundboard.model.SoundList
import com.mikkipastel.soundboard.model.Soundboard

fun setLocalSoundList(context: Context): ArrayList<Soundboard>? {
    val inputStream = context.assets.open("soundboard.json")
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()

    val json = String(buffer, charset("UTF-8"))
    val listType = object : TypeToken<SoundList>() {}.type
    val gson = Gson().fromJson<SoundList>(json, listType)
    return gson.sound
}