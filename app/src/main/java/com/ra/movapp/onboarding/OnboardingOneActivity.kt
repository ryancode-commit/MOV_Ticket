package com.ra.movapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ra.movapp.R
import com.ra.movapp.sign.signin.SignInActivity
import com.ra.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")){
            finishAffinity()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_next_one.setOnClickListener(this)
        btn_skip_one.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_next_one -> {
                val intent = Intent(this, OnboardingTwoActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_skip_one->{
                preferences.setValues("onboarding","1")
                finishAffinity()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}