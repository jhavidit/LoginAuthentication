package com.dsckiet.loginauthentication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var c: Int = 0
    var str = ""
    lateinit var pinManager: PinManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var str = ""
        pinManager = PinManager(this)
        if (pinManager.getPin().isNullOrEmpty()) {
            val i = Intent(this, SetPin::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        key_1.setOnClickListener {
            count("1")
        }
        key_2.setOnClickListener {
            count("2")
        }
        key_3.setOnClickListener {
            count("3")
        }
        key_4.setOnClickListener {
            count("4")
        }
        key_5.setOnClickListener {
            count("5")
        }
        key_6.setOnClickListener {
            count("6")
        }
        key_7.setOnClickListener {
            count("7")
        }
        key_8.setOnClickListener {
            count("8")
        }
        key_9.setOnClickListener {
            count("9")
        }
        key_0.setOnClickListener {
            count("0")
        }
        key_backspace.setOnClickListener {
            onBackPressed()
        }
        key_clear.setOnClickListener {
            clear()
        }

    }

    fun count(s: String) {
        if (c >= 4) {
            c = 4
        } else {
            str += s
            c++;
        }
        update(c, str)
    }

    fun clear() {
        c = 0;
        str = ""
        update(c, "")
    }

    fun update(c: Int, str: String) {

        val vibrate = AnimationUtils.loadAnimation(this, R.anim.vibrate)

        when (c) {

            0 -> {
                Set1()
            }
            1 -> {
                Set2()
            }
            2 -> {
                Set3()
            }
            3 -> {
                Set4()
            }
            4 -> {
                Set5()
            }
        }

        if (c == 4) {

            if (str == pinManager.getPin()) {
                val i = Intent(this, FakeActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            } else {
                indicators.startAnimation(vibrate)
                keyboard.startAnimation(vibrate)
                Shake()
                clear()
                indicator1.setColorFilter(Color.parseColor("#75DAFA"))
                indicator2.setColorFilter(Color.parseColor("#75DAFA"))
                indicator3.setColorFilter(Color.parseColor("#75DAFA"))
                indicator4.setColorFilter(Color.parseColor("#75DAFA"))
            }
        }
    }


    fun Shake() {
        val DURATION = 200
        if (Build.VERSION.SDK_INT >= 26) {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                VibrationEffect.createOneShot(DURATION.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                DURATION.toLong()
            )
        }
    }


    fun Set1() {
        indicator1.setColorFilter(Color.parseColor("#75DAFA"))
        indicator2.setColorFilter(Color.parseColor("#75DAFA"))
        indicator3.setColorFilter(Color.parseColor("#75DAFA"))
        indicator4.setColorFilter(Color.parseColor("#75DAFA"))
    }

    fun Set2() {
        indicator1.setColorFilter(Color.parseColor("#008DB9"))
        indicator2.setColorFilter(Color.parseColor("#75DAFA"))
        indicator3.setColorFilter(Color.parseColor("#75DAFA"))
        indicator4.setColorFilter(Color.parseColor("#75DAFA"))
    }

    fun Set3() {
        indicator1.setColorFilter(Color.parseColor("#008DB9"))
        indicator2.setColorFilter(Color.parseColor("#008DB9"))
        indicator3.setColorFilter(Color.parseColor("#75DAFA"))
        indicator4.setColorFilter(Color.parseColor("#75DAFA"))
    }

    fun Set4() {
        indicator1.setColorFilter(Color.parseColor("#008DB9"))
        indicator2.setColorFilter(Color.parseColor("#008DB9"))
        indicator3.setColorFilter(Color.parseColor("#008DB9"))
        indicator4.setColorFilter(Color.parseColor("#75DAFA"))
    }

    fun Set5() {
        indicator1.setColorFilter(Color.parseColor("#008DB9"))
        indicator2.setColorFilter(Color.parseColor("#008DB9"))
        indicator3.setColorFilter(Color.parseColor("#008DB9"))
        indicator4.setColorFilter(Color.parseColor("#008DB9"))

    }
}