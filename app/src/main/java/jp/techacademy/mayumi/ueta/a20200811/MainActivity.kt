package jp.techacademy.mayumi.ueta.a20200811

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.os.Handler
import android.view.View
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 角度保存用のメンバ変数
    var rot = 0.0f

    var errorcount = 0

    //初めのランドルト環の画像をいれる入れ物を作る
    var bitmapfirst: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bitmapfirst =changeimage()

        rotate_button.setOnClickListener {
            // 加工用に行列をコピー
            val tmpMatrix = imageView.imageMatrix
            // 保存している角度を戻す(ImageViewの中心で回転)
            tmpMatrix.postRotate(-rot, imageView.width * 0.5f, imageView.height * 0.5f)

            if (rot > 360) {
                rot = 0.0f
            }
            //角度設定
            rot += (0..4).random() * 90.0f

            //角度分回転させる(ImageViewの中心で回転)
            tmpMatrix.postRotate(rot, imageView.width * 0.5f, imageView.height * 0.5f)
            // 行列を適用
            imageView.imageMatrix = tmpMatrix
            imageView.invalidate()

        }

        Right_button.setOnClickListener {
                onClick (0.0f, it)
        }
        Left_button.setOnClickListener {
            onClick (180.0f, it)
        }
        Upper_button.setOnClickListener {
            onClick (270.0f, it)
            }
        Lower_button.setOnClickListener {
            onClick (90.0f, it)
        }

//画像を小さくするコマンド
        reduce_button.setOnClickListener {
            reduce ()
        }

//画像を大きくするコマンド
        extend_button.setOnClickListener {
            // changeimage関数で作成したbitmapを受け取る
            var bitmap = changeimage()
            //大きさを縦横2倍にした画像の設定
            var reducedimage =
                Bitmap.createScaledBitmap(bitmapfirst, bitmap.width * 2, bitmap.height * 2, true)
            //bitmapをimageViewに設定する
            imageView.setImageBitmap(reducedimage)
        }
    }
//imageViewの中にあるdrawable(画像）をbitmap形式にする
    private fun changeimage(): Bitmap{
        // imageViewの中にあるdrawable(画像）を変数にいれる
        var drawableImage = imageView.drawable
        // drawableImageからbitmapdrawableに変換
        var bitmapdrawable = drawableImage as BitmapDrawable
        //bitmapdrawableからbitmap形式で画像を取ってくる
        var bitmap = bitmapdrawable.bitmap
        return bitmap
    }

    private fun rotatechange () {
        Handler().postDelayed( {
        val tmpMatrix = imageView.imageMatrix
        // 保存している角度を戻す(ImageViewの中心で回転)
        tmpMatrix.postRotate(-rot, imageView.width * 0.5f, imageView.height * 0.5f)

        if (rot >= 360) {
            rot = 0.0f
        }
        //角度設定
        rot += (0..4).random() * 90.0f

        //角度分回転させる(ImageViewの中心で回転)
        tmpMatrix.postRotate(rot, imageView.width * 0.5f, imageView.height * 0.5f)
        // 行列を適用
        imageView.imageMatrix = tmpMatrix
        imageView.invalidate()
        }, 10);}

    private fun  reduce () {
        // changeimage関数で作成したbitmapを受け取る
        var bitmap = changeimage()
        //大きさを縦横半分にした画像の設定
        var reducedimage =
            Bitmap.createScaledBitmap(bitmapfirst, bitmap.width / 2, bitmap.height / 2, true)
        //bitmapをimageViewに設定する
        imageView.setImageBitmap(reducedimage)
        rot = 0.0f
    }
    private fun onClick (angle:Float, it:View){
        if (rot==angle || (rot==360.0f && angle==0.0f) ) {
            reduce()
            errorcount=0
        }
        else {
            errorcount++
            if (errorcount >=2) {
               //Snackbar.make(it, " Error", Snackbar.LENGTH_LONG).setAction("action",null).show()
               //errorcount=0
                val intent = Intent (this, ResultActivity::class.java)
                startActivity(intent)
                return
            }
    }
        rotatechange ()
    }

}

