package com.example.noteopencv.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteopencv.R
import kotlinx.android.synthetic.main.activity_simple_demo.*
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import java.io.IOException

class SimpleDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_demo)
        button.setOnClickListener {
            try {
                val desMat = Mat()
                val mat = Utils.loadResource(applicationContext,R.drawable.lena)
                Core.bitwise_not(mat,desMat)
                val resultBitmap = Bitmap.createBitmap(desMat.width(),desMat.height(),Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(desMat,resultBitmap)
                img.setImageBitmap(resultBitmap)
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // mat 对象建议在这里release下
       // mat.release()
    }
}