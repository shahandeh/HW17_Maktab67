package com.example.hw17_maktab67.di

import android.app.Application

class App: Application() {
    lateinit var serviceLocator: ServiceLocator
    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(this)
    }
}