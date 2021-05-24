package com.example.myfirstapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class growth : AppCompatActivity() {
    var CURRENT_VALUE = 100
    var CURRENT_RATE  = 0.0
    var helper = com.example.myfirstapp.helper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_growth)
//        testgetCurrentRate()
        this.pageReload()
    }

    private  fun pageReload() {
        CURRENT_VALUE = getCurrentValue()
        CURRENT_RATE = getCurrentRate()

        val value_text = findViewById<TextView>(R.id.current_text).apply {
            text = CURRENT_VALUE.toString()
        }

        findViewById<TextView>(R.id.rate_text).apply {
            text = String.format("%.2f", CURRENT_RATE * 100) + "%"
        }

        setValueText(0.0,0.0, 0.0, 0.0)
    }

    fun getCurrentValue():Int{
        return (1..100000).random()
    }

    /*
     * (0~20)70% (21~30)20% (30~100)10%
     */
    fun getCurrentRate():Double{
        val n = (1..10).random()
        var rate_n = 1

        if (n in (1..7)){
            rate_n = (0..20).random()
        }
        else if (n in (8..9)){
            rate_n = (21..30).random()
        }
        else{
//            Log.e("error", "impossible")
            rate_n = (31..100).random()
        }
        return  rate_n / 100.0
    }

    fun testgetCurrentRate(){
        var i = 0
        var r = -1.0
        var c10 = 0
        var c20 = 0
        var c30 = 0

        while (i<100){
            r = getCurrentRate()
            if (0 <= r && r <= 0.2){
                c10++
            }
            else if (0.21 <= r && r <= 0.3){
                c20++
            }
            else {
                c30++
            }
            Log.v("test", r.toString())
            i++
        }
        Log.v("test", "testgetCurrentRate: c10: "+c10.toString()+", c20: "+c20.toString()+", c30: "+c30.toString())
    }

    fun reloadBtn(view: View) {
        this.pageReload()
    }

    fun calcGrowth(view: View) {
        val est_text = findViewById<EditText>(R.id.estimate_value).getEditableText().toString().trim()
        val ESTIMATE_VALUE = est_text.trim().toDouble();
        val ACCURATE_VALUE = (CURRENT_VALUE * CURRENT_RATE) / (CURRENT_RATE + 1)

        val DEVIATION2 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 2), helper.roundAddN(ACCURATE_VALUE, 2))
        val DEVIATION3 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 3), helper.roundAddN(ACCURATE_VALUE, 3))
        val DEVIATION4 = helper.deviation(helper.roundAddN(ESTIMATE_VALUE, 4), helper.roundAddN(ACCURATE_VALUE, 4))
        setValueText(DEVIATION2, DEVIATION3, DEVIATION4, ACCURATE_VALUE)
    }

    private fun setValueText(DEVIATION2: Double, DEVIATION3: Double, DEVIATION4: Double, ACCURATE_VALUE:Double) {
        //保留两位小数，四舍五入
        findViewById<TextView>(R.id.deviation2_text).apply {
            text = String.format("%.2f", DEVIATION2) + "%"
        }

        findViewById<TextView>(R.id.deviation3_text).apply {
            text = String.format("%.2f", DEVIATION3) + "%"
        }

        findViewById<TextView>(R.id.deviation4_text).apply {
            text = String.format("%.2f", DEVIATION4) + "%"
        }

        //保留两位小数，四舍五入
        findViewById<TextView>(R.id.accurate_growth_text).apply {
            text = DecimalFormat("#.##").format(ACCURATE_VALUE)
        }
    }

}