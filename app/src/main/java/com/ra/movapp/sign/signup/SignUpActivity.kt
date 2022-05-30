package com.ra.movapp.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.ra.movapp.DetailActivity
import com.ra.movapp.R
import com.ra.movapp.sign.signin.User
import com.ra.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        btn_lanjutkan.setOnClickListener {
            sUsername = et_user_name.text.toString()
            sPassword = et_password_reg.text.toString()
            sNama = et_name.text.toString()
            sEmail = et_emailAddress.text.toString()

            if (sUsername.equals("")){
                et_user_name.error = "Silakan isi username anda"
                et_user_name.requestFocus()
            }else if (sPassword.equals("")){
                et_password_reg.error = "Silakan isi password anda"
                et_password_reg.requestFocus()
            }else if (sNama.equals("")){
                et_name.error = "Silakan isi nama anda"
                et_name.requestFocus()
            }else if (sEmail.equals("")){
                et_emailAddress.error = "Silakan isi email anda"
                et_emailAddress.requestFocus()
            }else{
                saveUsername(sUsername,sPassword,sEmail,sNama)
            }
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sEmail: String, sName: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.password = sPassword
        user.nama = sName

        if (sUsername != null){
            ChecingUsername(sUsername,user)
        }
    }

    private fun ChecingUsername(sUsername: String, data: User) {
        mFirebaseDatabase.child(sUsername).addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabase.child(sUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "500000")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    var intent = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("data", data)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@SignUpActivity, "User telah digunakan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}