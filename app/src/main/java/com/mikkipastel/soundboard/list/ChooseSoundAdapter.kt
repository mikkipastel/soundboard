package com.mikkipastel.soundboard.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding

class ChooseSoundAdapter(): RecyclerView.Adapter<ChooseSoundViewHolder>() {
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
        holder.bindView()
    }

    override fun getItemCount(): Int = 8
}