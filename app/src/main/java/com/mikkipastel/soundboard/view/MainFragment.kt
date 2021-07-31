package com.mikkipastel.soundboard.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource.Factory
import com.mikkipastel.soundboard.databinding.FragmentMainBinding
import com.mikkipastel.soundboard.model.SaveSoundPad
import com.mikkipastel.soundboard.model.Soundboard
import com.mikkipastel.soundboard.view.button.ButtonSoundAdapter
import com.mikkipastel.soundboard.view.button.ButtonSoundListener
import com.mikkipastel.soundboard.view.list.ChooseSoundBottomSheet

class MainFragment: Fragment(), ButtonSoundListener {

    private lateinit var binding: FragmentMainBinding

    private lateinit var buttonAdapter: ButtonSoundAdapter

    private lateinit var player: SimpleExoPlayer

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = SimpleExoPlayer.Builder(requireContext()).build()

        buttonAdapter = ButtonSoundAdapter(arrayListOf(
            SaveSoundPad(
                0,
                Soundboard(
                    "\uD83C\uDF89",
                    "tada",
                    "tada.mp3",
                )
            ),
            SaveSoundPad(1),
            SaveSoundPad(2),
            SaveSoundPad(
                3,
                Soundboard(
                    "\uD83E\uDDD2\uD83D\uDCE3",
                    "kids_cheering",
                    "kids_cheering.mp3",
                )
            ),
            SaveSoundPad(4),
            SaveSoundPad(5),
            SaveSoundPad(6),
            SaveSoundPad(7),
        ),this)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = buttonAdapter
        }
    }

    override fun playSound(mp3: String) {
        val path = "assets:///sound/$mp3"
        val dataSourceFactory = Factory { AssetDataSource(requireContext()) }
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(path))

        player.setMediaSource(mediaSource)
        player.prepare()
        player.playWhenReady = true
    }

    override fun chooseSound(padData: SaveSoundPad) {
        ChooseSoundBottomSheet.newInstance(padData)
            .show(
                parentFragmentManager,
                "chooseSoundBottomSheet"
            )
    }
}