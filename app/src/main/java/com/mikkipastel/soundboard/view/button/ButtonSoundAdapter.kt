package com.mikkipastel.soundboard.view.button

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemChooseSoundBinding
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding

class ButtonSoundAdapter(
    private val listener: ButtonSoundListener
): RecyclerView.Adapter<ButtonSoundViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonSoundViewHolder {
        return ButtonSoundViewHolder(
            ItemSoundButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ButtonSoundViewHolder, position: Int) {
        holder.bindView(listener)
    }

    override fun getItemCount(): Int = 8
}