package com.mikkipastel.soundboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikkipastel.soundboard.R
import com.mikkipastel.soundboard.databinding.ActivityMainBinding
import com.mikkipastel.soundboard.viewmodel.SoundPadViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val soundPadViewModel: SoundPadViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        soundPadViewModel.getSoundPad()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    MainFragment.newInstant()
                )
                .commit()
        }
    }
}