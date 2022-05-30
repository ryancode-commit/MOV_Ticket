package com.ra.movapp.sign.signup

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View.VISIBLE
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ra.movapp.home.HomeScreenActivity
import com.ra.movapp.R
import com.ra.movapp.sign.signin.User
import com.ra.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up_photo.*
import java.util.*

class SignUpPhotoActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var user : User
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        user = intent.getParcelableExtra("data")!!

        tv_username.text = user.nama

        btn_upload_photo.setOnClickListener{
            if (statusAdd){
                statusAdd = false
                btn_save_upload.visibility =  VISIBLE
                btn_upload_photo.setImageResource(R.drawable.ic_btn_upload)
                iv_photo_upload.setImageResource(R.drawable.userpict)
            }else{
//                Dexter.withActivity(this)
//                    .withPermission(Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()

                ImagePicker.with(this)
                    .cameraOnly()
                    .start()
            }
        }

        btn_lewati.setOnClickListener{
            finishAffinity()

            var goHome = Intent(this@SignUpPhotoActivity, HomeScreenActivity::class.java)
            startActivity(goHome)
        }

        btn_save_upload.setOnClickListener {
            if (filePath != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this@SignUpPhotoActivity, "Uploaded", Toast.LENGTH_SHORT).show()
                        ref.downloadUrl.addOnSuccessListener {
                            saveToFirebase(it.toString())
                        }
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this@SignUpPhotoActivity, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                    }
            }
        }
    }

    private fun saveToFirebase(url: String) {
        mFirebaseDatabase.child(user.username!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                user.url = url
                mFirebaseDatabase.child(user.username!!).setValue(user)

                preferences.setValues("nama", user.nama.toString())
                preferences.setValues("user", user.username.toString())
                preferences.setValues("saldo", "2000000")
                preferences.setValues("url", "")
                preferences.setValues("email", user.email.toString())
                preferences.setValues("status", "1")
                preferences.setValues("url", url)

                finishAffinity()
                val intent = Intent(this@SignUpPhotoActivity,
                    HomeScreenActivity::class.java)
                startActivity(intent)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpPhotoActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

//    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
//            takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }
//
//    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
//        Toast.makeText(this@SignUpPhotoActivity, "Anda tidak bisa menambahkan photo profile", Toast.LENGTH_SHORT)
//            .show()
//    }
//
//    override fun onPermissionRationaleShouldBeShown(
//        permission: PermissionRequest?,
//        token: PermissionToken?
//    ) {
//        TODO("Not yet implemented")
//    }

    override fun onBackPressed() {
        Toast.makeText(this@SignUpPhotoActivity, "Tergesah? klik tombol nanti saja", Toast.LENGTH_SHORT)
            .show()
    }

//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
//            var bitmap = data?.extras?.get("data") as Bitmap
//            statusAdd = true
//
//            filePath = data.getData()!!
//            Glide.with(this)
//                .load(bitmap)
//                .apply(RequestOptions.circleCropTransform())
//                .into(iv_photo_upload)
//
//            btn_save_upload.visibility = VISIBLE
//            btn_upload_photo.setImageResource(R.drawable.ic_btn_delete)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode ==  RESULT_OK){
            statusAdd = true

            filePath = data?.data!!
            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_photo_upload)

            btn_save_upload.visibility = VISIBLE
            btn_upload_photo.setImageResource(R.drawable.ic_btn_delete)
        }else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ""+ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Task Canceled", Toast.LENGTH_SHORT).show()
        }
    }
}