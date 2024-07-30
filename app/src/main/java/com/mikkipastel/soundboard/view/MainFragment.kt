package com.mikkipastel.soundboard.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.exoplayer2.MediaItem
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
import com.mikkipastel.soundboard.viewmodel.SoundPadViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment: Fragment(), ButtonSoundListener {

    private lateinit var binding: FragmentMainBinding

    private val soundPadViewModel: SoundPadViewModel by sharedViewModel()

    private lateinit var buttonAdapter: ButtonSoundAdapter

    private lateinit var player: SimpleExoPlayer

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = SimpleExoPlayer.Builder(requireContext()).build()
        buttonAdapter = ButtonSoundAdapter(this)

        attachObserver()
    }

    private fun attachObserver() {
        soundPadViewModel.soundPadList.observe(viewLifecycleOwner) {
            when (it.size == 0) {
                true -> soundPadViewModel.initDatabase()
                false -> initSoundPad(it)
            }
        }
        soundPadViewModel.soundPadUpdate.observe(viewLifecycleOwner) {
            buttonAdapter.apply {
                padList[it.position] = it
                notifyItemChanged(it.position)
            }
        }
    }

    private fun initSoundPad(padList: MutableList<SaveSoundPad>) {
        buttonAdapter.padList = padList
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = buttonAdapter
        }
    }

    override fun playSound(mp3: String) {
        val path = "assets:///sound/$mp3"
        val dataSourceFactory = Factory { AssetDataSource(requireContext()) }
        val mediaItem = MediaItem.fromUri(Uri.parse(path))
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)

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