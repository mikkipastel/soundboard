package com.mikkipastel.soundboard.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mikkipastel.soundboard.dao.SoundPadTable
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

@Entity(tableName = SoundPadTable)
@Parcelize
data class SaveSoundPad(
    @PrimaryKey(autoGenerate = true) @SerializedName("position") val position: Int,
    @Embedded @SerializedName("sound") val sound: Soundboard?
): Parcelable
