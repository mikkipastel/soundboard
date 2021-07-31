package com.mikkipastel.soundboard.view.button

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding
import com.mikkipastel.soundboard.model.SaveSoundPad

class ButtonSoundViewHolder(
    private val itemBinding: ItemSoundButtonBinding
) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindView(
        position: Int,
        pad: SaveSoundPad?,
        listener: ButtonSoundListener
    ) {
        itemBinding.apply {
            button.apply {
                setOnClickListener {
                    when (pad?.sound != null) {
                        true -> listener.playSound(pad?.sound?.mp3!!)
                        false -> listener.chooseSound(position)
                    }
                }
                setOnLongClickListener {
                    listener.chooseSound(position)
                    return@setOnLongClickListener false
                }
                button.text = pad?.sound?.emoji

                backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        root.context,
                        when (pad?.sound != null) {
                            true -> R.color.padEnable
                            false -> R.color.padDisable
                        }
                    )
                )
            }
        }
    }
}

interface ButtonSoundListener {
    fun playSound(mp3: String)
    fun chooseSound(padPosition: Int)
}