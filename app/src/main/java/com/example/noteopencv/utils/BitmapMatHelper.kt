package com.example.noteopencv.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import java.io.IOException

/**
 * Create by SunnyDay /12/22 17:32:41
 * 1、Bitmap to Mat
 * 2、Mat to Bitmap
 */
object BitmapMatHelper {


    fun getMatByResId(context: Context,@DrawableRes resId:Int){
//        val bp = BitmapFactory.decodeResource(context.resources,resId)
//        val mat = Mat()
//        Utils.bitmapToMat(bp,mat)

//        try {
//            val mat = Utils.loadResource(context,resId)
//        }catch (e:IOException){
//        }
    }
}