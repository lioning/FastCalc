package com.example.myfirstapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat


class divide : AppCompatActivity() {
//    var accurate_value  = 0.0
    var dividant = 1.0
    var divisor  = 2.0
    var helper = com.example.myfirstapp.helper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divide)

        pageReload()
    }

    private fun pageReload() {
        dividant = get_dividant()
        divisor = get_divisor()

        findViewById<TextView>(R.id.dividant_text).apply {
            text = dividant.toString()
        }

        findViewById<TextView>(R.id.divisor_text).apply {
            text = divisor.toString()
        }
        setValueText(0.0, 0.0, 0.0, 0.0)
    }

    private fun get_dividant():Double{
        return (100 until 100001).random().toDouble()
    }

    private fun get_divisor():Double{
        val candidate = arrayOf(20.0, 2.5, 3.3, 5.0, 5.3, 5.6, 5.9, 6.6, 7.7, 8.3, 9.1)
        candidate.plus(arrayOf(11.1, 12.5, 14.3, 15.0, 16.6))
        return candidate.random()
    }

    fun changeNumberBtn(view: View) {
        pageReload()
    }

    fun calcDevide(view: View) {
//        val est_text = helper.addZero(findViewById<EditText>(R.id.divide_estimate).getEditableText().toString().trim())
        val est_text = findViewById<EditText>(R.id.divide_estimate).getEditableText().toString().trim()
        val ESTIMATE_VALUE = est_text.trim().toDouble();
        val accurate_value = (dividant * 1.0) / divisor
        val devitation2 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 2), helper.roundAddN(accurate_value, 2))
        val devitation3 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 3), helper.roundAddN(accurate_value, 3))
        val devitation4 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 4), helper.roundAddN(accurate_value, 4))
        setValueText(devitation2, devitation3, devitation4, accurate_value)

    }

    private fun setValueText(
            devitation2: Double,
            devitation3: Double,
            devitation4: Double,
            accurate_value: Double
    ) {
        //保留两位小数，四舍五入
//        var tv = findViewById<TextView>(R.id.divide_deviation2_text).apply {
//            text = String.format("%.2f", devitation2) + "%"
//        }
        var tv = findViewById<TextView>(R.id.divide_deviation2_text)
        tv.setText(String.format("%.2f", devitation2) + "%")
        tv.setTextColor(helper.getTextColor(devitation2))

        tv = findViewById<TextView>(R.id.divide_deviation3_text).apply {
            text = String.format("%.2f", devitation3) + "%"
        }
        tv.setTextColor(helper.getTextColor(devitation3))

        tv = findViewById<TextView>(R.id.divide_deviation4_text).apply {
            text = String.format("%.2f", devitation4) + "%"
        }
        tv.setTextColor(helper.getTextColor(devitation4))

        //保留两位小数，四舍五入
        findViewById<TextView>(R.id.divide_accurate).apply {
            text = DecimalFormat("#.##").format(accurate_value)
        }
    }
}