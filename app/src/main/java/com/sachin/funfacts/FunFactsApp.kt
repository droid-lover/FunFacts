package com.sachin.funfacts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FunFactsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: FunFactsApp? = null
    }
}