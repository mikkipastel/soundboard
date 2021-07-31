package com.mikkipastel.soundboard.view.button

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding
import com.mikkipastel.soundboard.model.SaveSoundPad

class ButtonSoundAdapter(
    private val padList: ArrayList<SaveSoundPad>,
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
        holder.bindView(
            position,
            padList[position],
            listener
        )
    }

    override fun getItemCount(): Int = padList.size
}