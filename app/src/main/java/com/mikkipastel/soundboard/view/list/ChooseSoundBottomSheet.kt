package com.mikkipastel.soundboard.view.list

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource.Factory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.FragmentChooseSoundBottomBinding
import com.mikkipastel.soundboard.model.SaveSoundPad
import com.mikkipastel.soundboard.model.Soundboard
import com.mikkipastel.soundboard.utils.setLocalSoundList

class ChooseSoundBottomSheet: BottomSheetDialogFragment(), ChooseSoundListener {

    private lateinit var binding: FragmentChooseSoundBottomBinding
    private lateinit var soundListAdapter: ChooseSoundAdapter

    private lateinit var player: SimpleExoPlayer

    private val padData by lazy {
        arguments?.getParcelable<SaveSoundPad>(BUNDLE_PAD_DATA)
    }

    private var currentPlayPosition = -1
    private var currentChoosePosition = -1

    companion object {
        const val BUNDLE_PAD_DATA = "ChooseSoundBottomSheet:BUNDLE_PAD_DATA"

        fun newInstance(padData: SaveSoundPad) = ChooseSoundBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_PAD_DATA, padData)
            }
        }
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

        player = SimpleExoPlayer.Builder(requireContext()).build()

        soundListAdapter = ChooseSoundAdapter(
            padData!!,
            setLocalSoundList(requireContext()),
            this
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = soundListAdapter
        }
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    override fun playSound(position: Int, mp3: String?) {
        resetPlaySoundButton()

        val path = "assets:///sound/$mp3"
        val dataSourceFactory = Factory { AssetDataSource(requireContext()) }
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(path))

        player.addListener(object: Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (!isPlaying) {
                    resetPlaySoundButton()
                }
            }
        })
        player.setMediaSource(mediaSource)
        player.prepare()
        player.playWhenReady = true

        currentPlayPosition = position
    }

    override fun pauseSound() {
        player.playWhenReady = false
    }

    override fun updateSound(position: Int, soundboard: Soundboard) {
        resetChooseSound()

        //update viewmodel

        currentChoosePosition = position
    }

    private fun resetPlaySoundButton() {
        if (currentPlayPosition >= 0) {
            val holder = binding.recyclerView.findViewHolderForLayoutPosition(currentPlayPosition)
            soundListAdapter.setPlaySoundIcon(holder)
        }
    }

    private fun resetChooseSound() {
        if (currentChoosePosition >= 0) {
            val holder = binding.recyclerView.findViewHolderForLayoutPosition(currentChoosePosition)
            soundListAdapter.unCheckChooseSound(holder)
        }
    }

}