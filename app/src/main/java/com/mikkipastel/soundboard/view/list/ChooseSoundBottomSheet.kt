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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.FragmentChooseSoundBottomBinding
import com.mikkipastel.soundboard.model.SaveSoundPad
import com.mikkipastel.soundboard.model.Soundboard
import com.mikkipastel.soundboard.utils.setLocalSoundList
import com.mikkipastel.soundboard.viewmodel.SoundPadViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChooseSoundBottomSheet: BottomSheetDialogFragment(), ChooseSoundListener {

    private lateinit var binding: FragmentChooseSoundBottomBinding
    private lateinit var soundListAdapter: ChooseSoundAdapter

    private val soundPadViewModel: SoundPadViewModel by sharedViewModel()

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

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
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

        val soundList = setLocalSoundList(requireContext())
        soundListAdapter = ChooseSoundAdapter(
            padData!!,
            soundList,
            this
        )

        padData?.let { padData ->
            currentChoosePosition = soundList?.indexOf(padData.sound) ?: -1
        }


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = soundListAdapter
            setItemViewCacheSize(soundList?.size!!)
        }
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    override fun playSound(position: Int, mp3: String?) {
        soundListAdapter.playSoundMp3 = mp3

        val path = "assets:///sound/$mp3"
        val dataSourceFactory = Factory { AssetDataSource(requireContext()) }
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(path))

        player.addListener(object: Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (!isPlaying) {
                    soundListAdapter.playSoundMp3 = mp3
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
        soundListAdapter.chooseSound = soundboard

        soundPadViewModel.updateSoundPad(
            SaveSoundPad(
                position,
                soundboard
            )
        )

        currentChoosePosition = position
    }
}