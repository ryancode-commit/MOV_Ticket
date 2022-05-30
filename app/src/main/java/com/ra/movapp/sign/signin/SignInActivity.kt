package com.ra.movapp.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.ra.movapp.home.HomeScreenActivity
import com.ra.movapp.R
import com.ra.movapp.sign.signup.SignUpActivity
import com.ra.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    lateinit var iUsername :String
    lateinit var iPassword :String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferece: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferece = Preferences(this)

        preferece.setValues("onboarding","1")

        if (preferece.getValues("status").equals("1")){

            val intent = Intent(this@SignInActivity, HomeScreenActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btn_signin.setOnClickListener {


            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan tulis Username Anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan tulis Password Anda"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_signup.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()

                } else {
                    if (user.password.equals(iPassword)){
//                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG).show()
//


                        preferece.setValues("nama",user.nama.toString())
                        preferece.setValues("user",user.username.toString())
                        preferece.setValues("url",user.url.toString())
                        preferece.setValues("email",user.email.toString())
                        preferece.setValues("saldo",user.saldo.toString())
                        preferece.setValues("status","1")

                        val intent = Intent(this@SignInActivity, HomeScreenActivity::class.java)
                        startActivity(intent)
                        finishAffinity()

                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
