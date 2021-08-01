package com.mikkipastel.soundboard

import android.app.Application
import androidx.room.Room
import com.mikkipastel.soundboard.dao.SoundPadDatabase
import com.mikkipastel.soundboard.dao.SoundPadTable
import com.mikkipastel.soundboard.repository.SoundPadRepository
import com.mikkipastel.soundboard.repository.SoundPadRepositoryImpl
import com.mikkipastel.soundboard.viewmodel.SoundPadViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            val soundPadModule = module {
                single<SoundPadRepository> {
                    SoundPadRepositoryImpl(get())
                }
                viewModel { SoundPadViewModel(get()) }
            }
            val databaseModule = module {
                single { Room.databaseBuilder(
                    androidContext(),
                    SoundPadDatabase::class.java,
                    SoundPadTable
                ).build() }
                single { SoundPadDatabase.getSoundPadDatabase(get()).soundPadDao }
            }
            modules(listOf(soundPadModule, databaseModule))
        }
    }
}