package com.mikkipastel.soundboard.view.button

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding
import com.mikkipastel.soundboard.model.SaveSoundPad

class ButtonSoundViewHolder(
    private val itemBinding: ItemSoundButtonBinding
) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindView(
        pad: SaveSoundPad,
        listener: ButtonSoundListener
    ) {
        itemBinding.apply {
            button.apply {
                setOnClickListener {
                    when (pad.sound != null) {
                        true -> listener.playSound(pad.sound.mp3!!)
                        false -> listener.chooseSound(pad)
                    }
                }
                setOnLongClickListener {
                    if (pad.sound != null) {
                        listener.chooseSound(pad)
                    }
                    return@setOnLongClickListener false
                }
                button.text = pad.sound?.emoji

                background = ContextCompat.getDrawable(
                    root.context,
                    when (pad.sound != null) {
                        true -> R.drawable.background_button_pad_enable
                        false -> R.drawable.background_button_pad_disable
                    }
                )
            }
        }
    }
}

interface ButtonSoundListener {
    fun playSound(mp3: String)
    fun chooseSound(padData: SaveSoundPad)
}