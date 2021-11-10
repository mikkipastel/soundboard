package com.mikkipastel.soundboard.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.model.SaveSoundPad
import com.mikkipastel.soundboard.model.Soundboard

class ChooseSoundAdapter(
    private val padData: SaveSoundPad,
    private val soundList: ArrayList<Soundboard>?,
    private val listener: ChooseSoundListener
): RecyclerView.Adapter<ChooseSoundViewHolder>() {

    var chooseSound: Soundboard?= null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var playSoundMp3: String?= null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseSoundViewHolder {
        return ChooseSoundViewHolder(
            ItemChooseSoundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChooseSoundViewHolder, position: Int) {
        soundList?.get(position)?.let {
            holder.bindView(padData, it, position, listener)

            when (it == chooseSound) {
                true -> holder.checkChooseSound()
                false -> holder.unCheckChooseSound()
            }

            when (it.mp3 == playSoundMp3) {
                true -> holder.setPauseSoundIcon()
                false -> holder.setPlaySoundIcon()
            }
        }
    }

    override fun getItemCount(): Int = soundList?.size!!
}