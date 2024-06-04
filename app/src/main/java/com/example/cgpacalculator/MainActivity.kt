package com.example.cgpacalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.floor


class MainActivity : AppCompatActivity(){

    private lateinit var calcBtn :Button

    private lateinit var crss1 : EditText
    private lateinit var crss2 : EditText
    private lateinit var crss3 : EditText
    private lateinit var crss4 : EditText
    private lateinit var crss5 : EditText

    private lateinit var mrkss1 : EditText
    private lateinit var mrkss2 : EditText
    private lateinit var mrkss3 : EditText
    private lateinit var mrkss4 : EditText
    private lateinit var mrkss5 : EditText

    private lateinit var prvscgpa : EditText

    private lateinit var resulTv : TextView

    private fun calculate(crs:Int, mks:Int, i:Int, output:DoubleArray, arr1:DoubleArray, arr2:DoubleArray, arr3:DoubleArray, arr4:DoubleArray)
    {
        when (crs) {
            1 -> {
                if (mks in 8..15)
                {
                    for (j in 8..15)
                    {
                        if (j == mks)
                        {
                            output[i] = arr1[j - 8]
                            break
                        }
                    }
                } else if (mks in 16..20) {
                    output[i] = 4.0
                } else {
                    output[i] = 0.0
                }
            }
            2 -> {
                if (mks in 16..31)
                {
                    for (j in 16..31)
                    {
                        if (j == mks)
                        {
                            output[i] = arr2[j - 16]
                            break
                        }
                    }
                } else if (mks in 32..40) {
                    output[i] = 8.0
                } else {
                    output[i] = 0.0
                }
            }
            3 -> {
                if (mks in 24..47)
                {
                    for (j in 24..47)
                    {
                        if (j == mks)
                        {
                            output[i] = arr3[j - 24]
                            break
                        }
                    }
                } else if (mks in 48..60) {
                    output[i] = 12.0
                } else {
                    output[i] = 0.0
                }
            }
            4 -> {
                if (mks in 32..63)
                {
                    for (j in 32..63)
                    {
                        if (j == mks)
                        {
                            output[i] = arr4[j - 32]
                            break
                        }
                    }
                } else if (mks in 64..80) {
                    output[i] = 16.0
                } else {
                    output[i] = 0.0
                }
            }
        }
    }

    private fun emptyCheck(txt : EditText) : Int
    {
        if(TextUtils.isEmpty(txt.text.toString()))
        {
            return 0
        }
        return txt.text.toString().toInt()
    }
    private fun marksCheck(txt : EditText, credits :Int) : Int
    {
        if(TextUtils.isEmpty(txt.text.toString()))
        {
            return 0
        }
        else if(credits == 0)
        {
            return 0
        }
        return txt.text.toString().toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.titlebar)
        setSupportActionBar(toolbar)

        calcBtn = findViewById(R.id.btnCalc)

        crss1 = findViewById(R.id.chrss1)
        crss2 = findViewById(R.id.chrss2)
        crss3 = findViewById(R.id.chrss3)
        crss4 = findViewById(R.id.chrss4)
        crss5 = findViewById(R.id.chrss5)

        mrkss1 = findViewById(R.id.mrkss1)
        mrkss2 = findViewById(R.id.mrkss2)
        mrkss3 = findViewById(R.id.mrkss3)
        mrkss4 = findViewById(R.id.mrkss4)
        mrkss5 = findViewById(R.id.mrkss5)

        prvscgpa = findViewById(R.id.prvscgpa)

        resulTv = findViewById(R.id.result)


        calcBtn.setOnClickListener()
        {
            val crs1 = emptyCheck(crss1)
            val crs2 = emptyCheck(crss2)
            val crs3 = emptyCheck(crss3)
            val crs4 = emptyCheck(crss4)
            val crs5 = emptyCheck(crss5)

            val mks1 = marksCheck(mrkss1, crs1)
            val mks2 = marksCheck(mrkss2, crs2)
            val mks3 = marksCheck(mrkss3, crs3)
            val mks4 = marksCheck(mrkss4, crs4)
            val mks5 = marksCheck(mrkss5, crs5)

            var prv : Double
            prv = if(TextUtils.isEmpty(prvscgpa.text.toString())) {
                0.0
            } else {
                prvscgpa.text.toString().toDouble()
            }


            val arr1 = doubleArrayOf(1.0, 1.5, 2.0, 2.33, 2.67, 3.0, 3.33, 3.67)
            val arr2 = doubleArrayOf(2.0, 2.5, 3.0, 3.5, 4.0, 4.33, 4.67, 5.0, 5.33, 5.67, 6.0, 6.33, 6.67, 7.0, 7.33, 7.67)
            val arr3 = doubleArrayOf(3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.33, 6.67, 7.0, 7.33, 7.67, 8.0, 8.33, 8.67, 9.0, 9.33, 9.67, 10.0, 10.33, 10.67, 11.0, 11.33, 11.67)
            val arr4 = doubleArrayOf(4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.33, 8.67, 9.0, 9.33, 9.67, 10.0, 10.33, 10.67, 11.0, 11.33, 11.67, 12.0, 12.33, 12.67, 13.0, 13.33, 13.67, 14.0, 14.33, 14.67, 15.0, 15.33, 15.67)

            val output = DoubleArray(5) { 0.0 }
            var i = 0

            val credits = crs1 + crs2 + crs3 + crs4 + crs5
            var qualityPoints = 0.0

            calculate(crs1, mks1, i, output, arr1, arr2, arr3, arr4)

            i = 1
            calculate(crs2, mks2, i, output, arr1, arr2, arr3, arr4)
            i = 2
            calculate(crs3, mks3, i, output, arr1, arr2, arr3, arr4)
            i = 3
            calculate(crs4, mks4, i, output, arr1, arr2, arr3, arr4)
            i = 4
            calculate(crs5, mks5, i, output, arr1, arr2, arr3, arr4)
            for (j in 0 until 5)
            {
                qualityPoints += output[j]
            }
            val gpa = qualityPoints / credits
            if(prv == 0.0) {prv = gpa}
            val cgpa = floor(((gpa + prv) / 2.0) * 100.0) / 100.0
            println(credits)
            resulTv.text = "CGPA: $cgpa"

        }
    }



}