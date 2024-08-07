package com.mikkipastel.soundboard.view.button

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikkipastel.soundboard.databinding.ItemSoundButtonBinding
import com.mikkipastel.soundboard.model.SaveSoundPad

class ButtonSoundAdapter(
    private val listener: ButtonSoundListener
): RecyclerView.Adapter<ButtonSoundViewHolder>() {

    var padList: MutableList<SaveSoundPad> = mutableListOf()

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
            padList[position],
            listener
        )
    }

    override fun getItemCount(): Int = padList.size
}