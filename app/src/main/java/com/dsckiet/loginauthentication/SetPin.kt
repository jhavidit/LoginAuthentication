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
import kotlinx.android.synthetic.main.activity_main.indicator1
import kotlinx.android.synthetic.main.activity_main.indicator2
import kotlinx.android.synthetic.main.activity_main.indicator3
import kotlinx.android.synthetic.main.activity_main.indicator4
import kotlinx.android.synthetic.main.activity_main.indicators
import kotlinx.android.synthetic.main.activity_main.key_0
import kotlinx.android.synthetic.main.activity_main.key_1
import kotlinx.android.synthetic.main.activity_main.key_2
import kotlinx.android.synthetic.main.activity_main.key_3
import kotlinx.android.synthetic.main.activity_main.key_4
import kotlinx.android.synthetic.main.activity_main.key_5
import kotlinx.android.synthetic.main.activity_main.key_6
import kotlinx.android.synthetic.main.activity_main.key_7
import kotlinx.android.synthetic.main.activity_main.key_8
import kotlinx.android.synthetic.main.activity_main.key_9
import kotlinx.android.synthetic.main.activity_main.key_backspace
import kotlinx.android.synthetic.main.activity_main.key_clear
import kotlinx.android.synthetic.main.activity_main.keyboard
import kotlinx.android.synthetic.main.activity_set_pin.*


var c: Int = 0
var k: Int = 0
var str1 = ""
var str2 = ""
var flag = false
lateinit var pinManager: PinManager


class SetPin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)
        pinManager = PinManager(this)
        var str1 = ""
        var str2 = ""
        key_1.setOnClickListener {
            save("1")
        }
        key_2.setOnClickListener {
            save("2")
        }
        key_3.setOnClickListener {
            save("3")
        }
        key_4.setOnClickListener {
            save("4")
        }
        key_5.setOnClickListener {
            save("5")
        }
        key_6.setOnClickListener {
            save("6")
        }
        key_7.setOnClickListener {
            save("7")
        }
        key_8.setOnClickListener {
            save("8")
        }
        key_9.setOnClickListener {
            save("9")
        }
        key_0.setOnClickListener {
            save("0")
        }
        key_backspace.setOnClickListener {
            if (flag) {
                clear()
            } else {
                onBackPressed()
            }
        }
        key_clear.setOnClickListener {
            if (flag) {
                Set1()
                c = 4
                str2 = ""
                k = 4
            } else {
                clear()
            }
        }
    }

    fun save(s: String) {

        if (!flag) {
            str1 += s
            c++;
        } else {
            str2 += s
            c++;
        }
        k++
        update(c, str1, str2)
    }

    fun clear() {
        c = 0;
        str1 = ""
        str2 = ""
        k = 0
        flag = false
        text.text = "Set Lock Screen Password"
        Set1()
    }


    fun update(c: Int, str1: String, str2: String) {

        val vibrate = AnimationUtils.loadAnimation(this, R.anim.vibrate)

        when (c) {

            0 -> {
                Set1()
            }
            1, 5 -> {
                Set2()
            }
            2, 6 -> {
                Set3()
            }
            3, 7 -> {
                Set4()
            }
            4, 8 -> {
                Set5()
            }
        }
        if (c == 4) {
            flag = true
            text.text = "Confirm Lock Screen Password"
            Set1()
        }

        if (k % 8 == 0 && k != 0) {
            if (str1 == str2) {
                pinManager.savePin(str1)
                val i = Intent(this, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            } else {
                indicators.startAnimation(vibrate)
                keyboard.startAnimation(vibrate)
                Shake()
                clear()
                Set1()
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