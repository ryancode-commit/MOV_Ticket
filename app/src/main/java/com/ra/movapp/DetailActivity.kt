package com.ra.movapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.ra.movapp.checkout.ChooseActivity
import com.ra.movapp.home.dashboard.PlaysAdapter
import com.ra.movapp.model.Film
import com.ra.movapp.model.Plays
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase:DatabaseReference
    private var datalist = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        tv_judul_film.text = data?.judul
        tv_desc_film.text = data?.desc
        tv_genre_film.text = data?.genre
        tv_rate_film.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster_detail)

        rv_whos_play.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        getData()

        btn_pilih_bangku.setOnClickListener {
            var intent = Intent(this@DetailActivity,ChooseActivity::class.java).putExtra("data",data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()

                for (getDataSnapshot in snapshot.children){
                    var film = getDataSnapshot.getValue(Plays::class.java)
                    datalist.add(film!!)
                }

                rv_whos_play.adapter = PlaysAdapter(datalist){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}