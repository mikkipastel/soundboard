package com.mikkipastel.soundboard.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.model.Soundboard

class ChooseSoundAdapter(
    private val soundList: ArrayList<Soundboard>?,
    private val listener: ChooseSoundListener
): RecyclerView.Adapter<ChooseSoundViewHolder>() {
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
        soundList?.get(position)?.let { holder.bindView(it, listener) }
    }

    override fun getItemCount(): Int = soundList?.size!!
}