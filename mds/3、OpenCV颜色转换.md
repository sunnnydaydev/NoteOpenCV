# OpenCV 颜色转换

###### 1、OpenCV图像色彩模式

- 位图模式：
- 灰度模式:RGB三通道的像素值通过一定的算法处理后相等。
- RGB模式
- HSV模式

###### 2、灰度图栗子

```kotlin
class ColorChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_change)
        changeColor.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources,R.drawable.girl)
            val srcMat = Mat()
            val desMat = Mat()
            Utils.bitmapToMat(bitmap,srcMat)
            //灰度处理
            Imgproc.cvtColor(srcMat,desMat,Imgproc.COLOR_BGR2GRAY)
            Utils.matToBitmap(desMat,bitmap)
            imgGirl.setImageBitmap(bitmap)
        }

    }
}
```

