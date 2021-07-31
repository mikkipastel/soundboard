package com.mikkipastel.soundboard.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.FragmentChooseSoundBottomBinding
import com.mikkipastel.soundboard.model.Soundboard
import com.mikkipastel.soundboard.utils.setLocalSoundList

class ChooseSoundBottomSheet: BottomSheetDialogFragment(), ChooseSoundListener {

    private lateinit var binding: FragmentChooseSoundBottomBinding
    private lateinit var soundListAdapter: ChooseSoundAdapter

    companion object {
        fun newInstance() = ChooseSoundBottomSheet()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChooseSoundBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        soundListAdapter = ChooseSoundAdapter(
            setLocalSoundList(requireContext()),
            this
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = soundListAdapter
        }
    }

    override fun playSound(mp3: String?) {
        //play with player
    }

    override fun updateSound(soundboard: Soundboard) {
        //update viewmodel
    }

}