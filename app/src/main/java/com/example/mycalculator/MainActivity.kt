package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNum = false
    var lastDot = false
    var lastOp = false
    var neg = false
    var inf = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        if(inf) {
            tvInput.append((view as Button).text)
            lastNum = true
        }
    }

    fun onClear(view: View){
        tvInput.text = ""
        lastNum = false
        lastDot = false
        lastOp = false
        neg = false
        inf = true
    }

    fun onDecimalPoint(view: View){
        if(lastNum && !lastDot){
            tvInput.append(".")
            lastDot = true
        }
    }

    fun onOperator(view: View){
        if(!lastOp && lastNum){
            tvInput.append((view as Button).text)
            lastOp = true
            lastNum = false
            lastDot = false
            neg = false
        }else if(!lastNum && !neg){
            if((view as Button).text.toString().toCharArray()[0] == '-'){
                tvInput.append((view as Button).text)
                neg = true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View){
        if(lastNum && lastOp){
            try {
                if(tvInput.text.contains("+")){
                    val l1 = tvInput.text.split("+")
                    val n1 = l1[0].toDouble()
                    val n2 = l1[1].toDouble()
                    val res = n1 + n2
                    tvInput.text = res.toString()
                    lastNum = true
                    neg = false
                    lastOp = false
                    lastDot = false
                }else if(tvInput.text.contains("*")){
                    val l1 = tvInput.text.split("*")
                    val n1 = l1[0].toDouble()
                    val n2 = l1[1].toDouble()
                    val res = n1 * n2
                    tvInput.text = res.toString()
                    lastNum = true
                    neg = false
                    lastOp = false
                    lastDot = false
                }else if(tvInput.text.contains("/")){
                    val l1 = tvInput.text.split("/")
                    val n1 = l1[0].toDouble()
                    val n2 = l1[1].toDouble()
                    val res: Double
                    if(n2 != 0.0) {
                        res = n1 / n2
                        tvInput.text = res.toString()
                        lastNum = true
                        neg = false
                        lastOp = false
                        lastDot = false
                    }
                    else {
                        tvInput.text = "INFINITY"
                        inf = false
                        lastNum = false
                        neg = true
                    }
                }else if(tvInput.text.contains("-")){
                    var n1 = 1.0
                    var n2 = 1.0
                    var num = 0

                    if(tvInput.text[0] == '-')
                        n1 = -1.0

                    for(i in 1 until tvInput.text.length){
                        if(tvInput.text[i] == '-')num++
                    }

                    val l = tvInput.text.split("-")

                    n2 = if(num == 2){
                        -1.0*l[2].toDouble()
                    }else{
                        l[1].toDouble()
                    }

                    n1 *= l[0].toDouble()

                    val res = n1 - n2

                    tvInput.text = res.toString()
                    lastNum = true
                    neg = false
                    lastOp = false
                    lastDot = false
                }else{
                    tvInput.text = "0"
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }else{
            tvInput.text = "0"
        }
    }
}