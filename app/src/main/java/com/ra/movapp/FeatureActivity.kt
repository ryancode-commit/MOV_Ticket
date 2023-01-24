package com.ra.movapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)


    }

    fun addNumber(a:Int,b:Int):Int{
        return a +  b
    }

    fun addNumber2(a:Int,b:Int):Int{
        return a +  b
    }

    //penambhan
}