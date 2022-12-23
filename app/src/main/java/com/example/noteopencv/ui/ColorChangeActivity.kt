package com.example.noteopencv.ui

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteopencv.R
import kotlinx.android.synthetic.main.activity_color_change.*
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import timber.log.Timber

class ColorChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_change)
        changeColor.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources,R.drawable.girl)
            val srcMat = Mat()
            val desMat = Mat()
            Utils.bitmapToMat(bitmap,srcMat)
            Imgproc.cvtColor(srcMat,desMat,Imgproc.COLOR_BGR2GRAY)
            Utils.matToBitmap(desMat,bitmap)
            imgGirl.setImageBitmap(bitmap)
        }

    }
}