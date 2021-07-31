package com.mikkipastel.soundboard.view.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.model.Soundboard

class ChooseSoundViewHolder(
    private val itemBinding: ItemChooseSoundBinding
) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindView(
        position: Int,
        soundboard: Soundboard,
        listener: ChooseSoundListener
    ) {
        itemBinding.apply {
            root.setOnClickListener {
                if (imageChoose.visibility == View.GONE) {
                    checkChooseSound()
                    listener.updateSound(soundboard)
                }
            }
            iconPlayPause.setOnClickListener {
                when (
                    iconPlayPause.drawable.constantState == ContextCompat.getDrawable(
                        root.context,
                        R.drawable.ic_play
                    )?.constantState
                ) {
                    true -> {
                        setPauseSoundIcon()
                        listener.playSound(position, soundboard.mp3)
                    }
                    false -> {
                        setPlaySoundIcon()
                        listener.pauseSound()
                    }
                }
            }
            textSoundName.text = "${soundboard.emoji} ${soundboard.name}"
            //imageChoose.visibility : check with choose sound
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

    private fun setPauseSoundIcon() {
        itemBinding.iconPlayPause.setImageDrawable(
            ContextCompat.getDrawable(
                itemBinding.root.context,
                R.drawable.ic_pause
            )
        )
    }

    private fun checkChooseSound() {
        itemBinding.imageChoose.visibility = View.VISIBLE
    }

    fun unCheckChooseSound() {
        itemBinding.imageChoose.visibility = View.GONE
    }
}

interface ChooseSoundListener {
    fun playSound(position: Int, mp3: String?)
    fun pauseSound()
    fun updateSound(soundboard: Soundboard)
}