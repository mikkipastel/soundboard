package com.mikkipastel.soundboard.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SoundList(
    @SerializedName("sound") val sound: ArrayList<Soundboard>?
): Parcelable

@Parcelize
data class Soundboard(
    @SerializedName("emoji") val emoji: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("mp3") val mp3: String?
): Parcelable

@Parcelize
data class SaveSoundPad(
    @SerializedName("position") val position: Int,
    @SerializedName("sound") val sound: Soundboard? = null
): Parcelable
