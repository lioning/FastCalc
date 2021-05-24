package com.example.myfirstapp

import android.graphics.Color
import java.lang.Math.abs
import java.text.DecimalFormat
import java.text.NumberFormat

class helper {
    /*
 * 取浮点数的前N位数字，四舍五入，不足N位的补零，不包括小数点
 */
    public fun roundAddN(value: Double, n: Int = 2):Double {

        if(value.equals(0.0)){
            return value
        }
        /* 输入 (1223.1,4) 数字较大时 toString 会转为科学计数法 1.2231E7
        * 输入 (0.000005, 2) 输出 0.0，不符合预期 50
        * */
        /*
        val value_text = DecimalFormat("#").format(
                ((value * pow(10.0, n * 1.0)).toString().substring(0, n + 1).toFloat())
                        / 10.0)
         */
        // 去除前导的 0，形成科学记数法有效数字的形式
        var v = double2string(value).trimStart('0').trimStart('.').toDouble()

        /*
         * 取 n+1 位，取得n位整数及一位小数的形式，用于后续四舍五入
         * 通过放大 10^n 倍，再通过转化为字符串，获得 n+1 位有效数字
         */

        val value_text = DecimalFormat("#").format(
                ( double2string(v * Math.pow(10.0, n * 1.0)).substring(0, n + 1).toFloat())
                        / 10.0)

        return value_text.toDouble()
    }

    public fun double2string(value:Double):String {
//        val s = "10000339".toDouble()
        val s = value
        val nf: NumberFormat = NumberFormat.getInstance()
        //去掉科学计数法显示，避免显示为111,111,111,111
        nf.setGroupingUsed(false)
        //设置数的小数部分所允许的最大位数，避免小数位被舍掉
        nf.setMaximumFractionDigits(15);
        //设置数的小数部分所允许的最小位数，避免小数位有多余的0
        nf.setMinimumFractionDigits(0);

        return nf.format(s)
    }

    public fun addZero(value_t: String, num: Int = 4): String {
        var value_t1 = value_t
        val len = value_t1.length
        for (i in 0..(4 - len)) {
            value_t1 = value_t1 + "0"
        }
        return value_t1
    }

    /*
     * 计算前一个数相对与后一个数的百分比差
     */
    public fun deviation(n: Double, m: Double):Double {
        return ((n-m)/m)*100
    }

    public fun test(){
        var tv = 1223.1
        var te = roundAddN(tv, 3)
        te = roundAddN(tv, 4)

        tv = 0.0002
        te = roundAddN(tv,2)
        tv = 1.23456
        te = roundAddN(tv)

        tv = 1.23456
        te = roundAddN(tv, 3)

        tv = 12.3456
        te = roundAddN(tv, 3)

        tv = 1.0
        te = roundAddN(tv, 4)

        tv = 1.0006
        te = roundAddN(tv, 4)
    }

    public fun getTextColor(devitation: Double):Int{
        val d = abs(devitation)
        // 红色
        var color = "#F44336"
        if (d<2){
            //绿色
            color = "#4CAF50"
        }
        else if (d < 10){
            //黄色
//            color = "#FFEB3B"
            color = "#FFC0CB"
        }
        return Color.parseColor(color)
    }
}