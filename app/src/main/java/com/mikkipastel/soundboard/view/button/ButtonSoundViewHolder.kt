package com.mikkipastel.soundboard.view.button

import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding

class ButtonSoundViewHolder(
    private val itemBinding: ItemSoundButtonBinding
) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindView(
        listener: ButtonSoundListener
    ) {
        itemBinding.apply {
            button.apply {
                setOnClickListener {
                    listener.playSound()
                }
                setOnLongClickListener {
                    listener.chooseSound()
                    return@setOnLongClickListener false
                }
            }
            //textEmoji
        }
    }
}

interface ButtonSoundListener {
    fun playSound()
    fun chooseSound()
}