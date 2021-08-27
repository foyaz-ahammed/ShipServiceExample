package com.kforce.shipservice.global

import android.app.Application
import com.kforce.shipservice.modules.networkModule
import com.kforce.shipservice.modules.repositoryModule
import com.kforce.shipservice.modules.viewModelModule
import org.koin.core.context.startKoin

class ShipApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //Start koin modules
        startKoin {
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}