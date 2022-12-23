# OpenCV 基础

###### 1、OpenCV Java Api 中常用类

- Mat：Android中对图像的处理使用Bitmap数据结构，而openCV中则使用Mat这种数据结构。因此常规的Bitmap位图在OpenCV中需要转换为Mat
- Core：主要用于Mat运算，提供了很多运算功能的静态函数。
- ImgProc：主要用于图像的处理，也提供了很多处理功能的静态函数。
- Utils：工具类，主要用于Bitmap与Mat之间的转化。

###### 2、Bitmap与Mat的转换

```kotlin
        val bp = BitmapFactory.decodeResource(context.resources,resId)
        val mat = Mat()
        //将Bitmap与mat绑定
        Utils.bitmapToMat(bp,mat)
```

```kotlin
        try {
            // 直接得到一个Mat对象
            val mat = Utils.loadResource(context,resId)
        }catch (e:IOException){
        }
```

```kotlin
        val bp = BitmapFactory.decodeResource(context.resources,resId)
        //openCV处理后的mat，把其变为bp在Android中显示
        Utils.matToBitmap(mat,bp)
```

###### 3、Mat的位运算和算术运算

Mat格式的图像可以进行位运算和算术运算。这些运算实际上都是矩阵运算。

Mat的运算被封装在Core类中

```java
//位运算

public static void bitwise_not(Mat src, Mat dst)// 按位非
public static void bitwise_and(Mat src1, Mat src2, Mat dst)//按位与
public static void bitwise_or(Mat src1, Mat src2, Mat dst) //按位或
public static void bitwise_xor(Mat src1, Mat src2, Mat dst)//按位异或

//算术运算
public static void add(Mat src1, Mat src2, Mat dst) //矩阵加法
public static void subtract(Mat src1, Mat src2, Mat dst) //矩阵减法
public static void multiply(Mat src1, Mat src2, Mat dst) //矩阵乘法
public static void divide(Mat src1, Mat src2, Mat dst) //矩阵除法
```

按位非栗子 ->

```kotlin
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
```
![](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/opencv/lena.png)


