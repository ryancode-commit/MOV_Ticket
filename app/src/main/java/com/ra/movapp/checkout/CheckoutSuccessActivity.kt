package com.ra.movapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ra.movapp.R
import com.ra.movapp.home.HomeScreenActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_home.setOnClickListener {
            finishAffinity()

            var intent = Intent(this@CheckoutSuccessActivity, HomeScreenActivity::class.java)
            startActivity(intent)
        }
    }
}