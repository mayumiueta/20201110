package jp.techacademy.mayumi.ueta.a20200811

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_select_eye.*

class SelectEyeActivity : AppCompatActivity() {

    var selectEye:String? = null
    var distance:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_eye)

        Start_button.setOnClickListener {
            if (selectEye == null || distance == null) {
                Snackbar.make(it, " 左右どちらの眼を測定するか、遠見か近見かどちらの視力を測定するか選んでください", Snackbar.LENGTH_LONG)
                    .setAction("action", null).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("selectEye", selectEye)
                intent.putExtra("Distance", distance)
                startActivity(intent)
            }
        }

        right_eye_button.setOnClickListener {
            selectEye="right"
            right_eye_button.isSelected = true
            left_eye_button.isSelected = false
        }

        left_eye_button.setOnClickListener {
            selectEye="left"
            left_eye_button.isSelected = true
            right_eye_button.isSelected = false
        }

        far_vision_button.setOnClickListener {
            distance="Far"
            far_vision_button.isSelected = true
            near_vision_button.isSelected = false
        }

        near_vision_button.setOnClickListener {
            distance="Near"
            near_vision_button.isSelected = true
            far_vision_button.isSelected = false
        }
    }
}
