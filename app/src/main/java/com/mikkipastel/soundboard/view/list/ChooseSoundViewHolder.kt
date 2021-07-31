package com.mikkipastel.soundboard.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.model.Soundboard

class ChooseSoundViewHolder(
    private val itemBinding: ItemChooseSoundBinding
) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindView(
        soundboard: Soundboard,
        listener: ChooseSoundListener
    ) {
        itemBinding.apply {
            root.setOnClickListener {
                //clear other adapter
                when (imageChoose.visibility == View.GONE) {
                    true -> {
                        imageChoose.visibility = View.VISIBLE
                        listener.updateSound(soundboard)
                    }
                    false -> {
                        imageChoose.visibility = View.GONE
                    }
                }
            }
            iconPlayPause.setOnClickListener {
                listener.playSound(soundboard.mp3)
            }
            textSoundName.text = "${soundboard.emoji} ${soundboard.name}"
            //imageChoose.visibility : check with choose sound
        }
    }
}

interface ChooseSoundListener {
    fun playSound(mp3: String?)
    fun updateSound(soundboard: Soundboard)
}