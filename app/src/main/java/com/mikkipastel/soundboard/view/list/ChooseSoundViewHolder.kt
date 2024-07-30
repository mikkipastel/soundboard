package com.mikkipastel.soundboard.view.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.model.SaveSoundPad
import com.mikkipastel.soundboard.model.Soundboard
import com.mikkipastel.soundboard.utils.pixelsEqualTo

class ChooseSoundViewHolder(
    private val itemBinding: ItemChooseSoundBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(
        padData: SaveSoundPad,
        soundboard: Soundboard,
        position: Int,
        listener: ChooseSoundListener
    ) {
        itemBinding.apply {
            root.setOnClickListener {
                if (imageChoose.visibility == View.GONE) {
                    checkChooseSound()
                    listener.updateSound(padData.position, soundboard)
                }
            }
            iconPlayPause.setOnClickListener {
                if (iconPlayPause.drawable.pixelsEqualTo(
                        ContextCompat.getDrawable(root.context, R.drawable.ic_play))
                    ) {
                    setPauseSoundIcon()
                    listener.playSound(position, soundboard.mp3)
                } else {
                    setPlaySoundIcon()
                    listener.pauseSound()
                }
            }
            textSoundName.text = "${soundboard.emoji} ${soundboard.name}"

            if (soundboard == padData.sound) {
                imageChoose.visibility = View.VISIBLE
            }
        }
    }

    fun setPlaySoundIcon() {
        itemBinding.iconPlayPause.setImageDrawable(
            ContextCompat.getDrawable(
                itemBinding.root.context,
                R.drawable.ic_play
            )
        )
    }

    fun setPauseSoundIcon() {
        itemBinding.iconPlayPause.setImageDrawable(
            ContextCompat.getDrawable(
                itemBinding.root.context,
                R.drawable.ic_pause
            )
        )
    }

    fun checkChooseSound() {
        itemBinding.imageChoose.visibility = View.VISIBLE
    }

    fun unCheckChooseSound() {
        itemBinding.imageChoose.visibility = View.GONE
    }
}

interface ChooseSoundListener {
    fun playSound(position: Int, mp3: String?)
    fun pauseSound()
    fun updateSound(position: Int, soundboard: Soundboard)
}