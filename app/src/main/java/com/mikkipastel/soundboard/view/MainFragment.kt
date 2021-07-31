package com.mikkipastel.soundboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mikkipastel.soundboard.databinding.FragmentMainBinding
import com.mikkipastel.soundboard.view.button.ButtonSoundAdapter
import com.mikkipastel.soundboard.view.button.ButtonSoundListener
import com.mikkipastel.soundboard.view.list.ChooseSoundBottomSheet

class MainFragment: Fragment(), ButtonSoundListener {

    private lateinit var binding: FragmentMainBinding

    private val buttonAdapter = ButtonSoundAdapter(this)

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

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = buttonAdapter
        }
    }

    override fun playSound() {
        //play with player
    }

    override fun chooseSound() {
        ChooseSoundBottomSheet.newInstance()
            .show(
                parentFragmentManager,
                "chooseSoundBottomSheet"
            )
    }
}