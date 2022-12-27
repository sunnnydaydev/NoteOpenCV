package com.example.noteopencv.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteopencv.R
import kotlinx.android.synthetic.main.activity_face_detection.*
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer

class FaceDetectionActivity : AppCompatActivity() {
    private lateinit var cascadeClassifier: CascadeClassifier
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_detection)
        initClassifier()


        faceDetect.setOnClickListener {
           faceDeDetect(BitmapFactory.decodeResource(resources,R.drawable.girl))
        }


    }

    /**
     * 初始化级联分类器
     * */
    private fun initClassifier() {
        try {
            val ins = resources.openRawResource(R.raw.lbpcascade_frontalface_improved)
            val cascadeDir = getDir("cascade", Context.MODE_PRIVATE)
            val cascadeFile = File(cascadeDir, "lbpcascade_frontalface_improved.xml")
            val os = FileOutputStream(cascadeFile)
            val buffer = ByteArray(4096)
            var byteCount = ins.read(buffer)
            while (byteCount != -1) {
                os.write(buffer, 0, byteCount)
                byteCount = ins.read(buffer)
            }
            ins.close()
            os.close()
            cascadeClassifier = CascadeClassifier(cascadeFile.absolutePath)
            cascadeFile.delete()
            cascadeDir.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun faceDeDetect(src:Bitmap){
        val matSrc = Mat()
        val matDes = Mat()
        val matGray = Mat()

        val faces = MatOfRect()

        Utils.bitmapToMat(src,matSrc)
        Imgproc.cvtColor(matSrc,matGray, Imgproc.COLOR_BGRA2GRAY)
        cascadeClassifier.detectMultiScale(matGray,faces,1.05,3,0,Size(30.0,30.0),Size())

        val faceList = faces.toList()
        matSrc.copyTo(matDes)
        if (faceList.size>0){
            for (rect in faceList){
                Imgproc.rectangle(matDes,rect.tl(),rect.br(), Scalar(255.0,0.0,0.0,255.0),4,8,0)
            }
        }
        val desBitmap = Bitmap.createBitmap(src.width,src.height,Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(matDes,desBitmap)

        faceImg.setImageBitmap(desBitmap)
        matDes.release()
        matGray.release()
        matSrc.release()
    }
}