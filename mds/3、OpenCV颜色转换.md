# OpenCV 颜色转换

###### 1、OpenCV图像色彩模式

- 位图模式：只有黑或白两种颜色。也即颜色值为0或255
- 灰度模式: RGB三通道的像素值通过一定的算法处理后相等。注意位图模式的颜色值为0或255，灰度模式的值介于0-255之间。
- RGB模式: 红绿蓝三种颜色
- HSV模式

###### 2、图片灰度处理

```kotlin
class ColorChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_change)
        changeColor.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl)
            val srcMat = Mat()
            val desMat = Mat()
            Utils.bitmapToMat(bitmap, srcMat)
            //灰度处理
            Imgproc.cvtColor(srcMat, desMat, Imgproc.COLOR_BGR2GRAY)
            Utils.matToBitmap(desMat, bitmap)
            imgGirl.setImageBitmap(bitmap)
        }

    }
}
```

![](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/opencv/girl.png)

###### 3、图像二值化

二值化就是将灰度图转化为位图模式，也就是将一张图片呈现出明显的黑白效果的过程。

灰度图的RGB像素点相等且介于0-255之间，二值化就是采取一定的算法将灰度值设置为0或255。

图像二值化的关键是确定一个阈值，整张图片中高于阈值的点都置为255，低于阈值的点都置为0，这样就可呈现出明显的二值效果。

阈值的确定有两种方法：

（1）手动阈值法

```kotlin
/**
 *  src：源位图（必须是一张灰度图）
 *  des：要转化的二值图
 *  thresh：阈值
 *  maxval：当type为THRESH_BINARY或THRESH_BINARY_INV时，所代表二值的最大值（二值时一般设置为255）
 *   type：一般设置为THRESH_BINARY表示求二值
 */
public static double threshold (Mat src, Mat dst, double thresh, double maxval, int type)
```

```kotlin
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl)
val srcMat = Mat()
val desMat = Mat()
Utils.bitmapToMat(bitmap, srcMat)
//1、先灰度
Imgproc.cvtColor(srcMat, desMat, Imgproc.COLOR_BGR2GRAY)
//2、再二值
Imgproc.threshold(desMat, desMat, 125.0, 255.0, Imgproc.THRESH_BINARY)
Utils.matToBitmap(desMat, bitmap)
imgGirl.setImageBitmap(bitmap)
```

![](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/opencv/img5.png)

（2）自动阈值法

openCV可以使用算法来自动计算阈值，其支持均值算法、高斯均值算法。他不是像手动指定那样计算全局的阈值，二值根据图像的不同区域亮度分布计算其局部 阈值，能够自适应不同区域的阈值。

```kotlin
/**
 * src：
 * dst：
 * maxValue：最大值，高于阈值时的像素最大值。同自动阈值maxval字段
 * adaptiveMethod：自适应算法（两种可选）
 * thresholdType：同自动阈值type字段
 * blockSize：区域局部大小（一般用奇数）
 * C：使用均值算法最后均值会减去一个常数，c就是常数的大小
 */
public static void adaptiveThreshold (
        Mat src,
Mat dst,
double maxValue,
int adaptiveMethod,
int thresholdType,
int blockSize,
double C)
```

```kotlin
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girl)
val srcMat = Mat()
val desMat = Mat()
Utils.bitmapToMat(bitmap, srcMat)
//1、先灰度
Imgproc.cvtColor(srcMat, desMat, Imgproc.COLOR_BGR2GRAY)
//2、再二值
Imgproc.adaptiveThreshold(
    desMat,
    desMat,
    255.0,
    Imgproc.ADAPTIVE_THRESH_MEAN_C,
    Imgproc.THRESH_BINARY,
    13,
    5.0
)
Utils.matToBitmap(desMat, bitmap)
imgGirl.setImageBitmap(bitmap)
```

![](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/opencv/img6.png)

              