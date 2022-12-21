package com.example.noteopencv

import android.app.Application
import org.opencv.android.OpenCVLoader
import timber.log.Timber

/**
 * Create by SunnyDay /12/21 17:42:21
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
        initOpenCV()
    }
    private fun initOpenCV(){
        val success = OpenCVLoader.initDebug()
        if (success){
           Timber.d("openCV init success")
        }else{
            Timber.d("openCV init failure")
        }
    }
    private fun initLog(){
        Timber.plant(Timber.DebugTree())
    }
}