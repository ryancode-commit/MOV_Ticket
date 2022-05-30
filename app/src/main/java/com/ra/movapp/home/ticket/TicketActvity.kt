package com.ra.movapp.home.ticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ra.movapp.R
import com.ra.movapp.model.Checkout
import com.ra.movapp.model.Film
import kotlinx.android.synthetic.main.activity_ticket_actvity.*

class TicketActvity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_actvity)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title_tiket.text = data?.judul
        tv_genre_tiiket.text = data?.genre
        tv_rate_tiket.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster_tiket)

        rv_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1",""))
        dataList.add(Checkout("C2",""))

        rv_checkout.adapter = TiketAdapter(dataList){
            
        }

        iv_qrCode.setOnClickListener {
            QRcode.visibility = VISIBLE
        }

        btn_tutup.setOnClickListener {
            QRcode.visibility = GONE
        }
    }
}