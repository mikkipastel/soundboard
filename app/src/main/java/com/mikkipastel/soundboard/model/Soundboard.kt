package com.mikkipastel.soundboard.model

import com.google.gson.annotations.SerializedName

data class SoundList(
    @SerializedName("sound") val sound: ArrayList<Soundboard>?
)

data class Soundboard(
    @SerializedName("emoji") val emoji: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("mp3") val mp3: String?
)
