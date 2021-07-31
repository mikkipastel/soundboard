package com.mikkipastel.soundboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikkipastel.soundboard.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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