package com.ra.movapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.ra.movapp.R
import com.ra.movapp.model.Checkout
import com.ra.movapp.model.Film
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity(), View.OnClickListener {

    var statusA1:Boolean = false
    var statusA2:Boolean = false
    var statusA3:Boolean = false
    var statusA4:Boolean = false

    var statusB1:Boolean = false
    var statusB2:Boolean = false
    var statusB3:Boolean = false
    var statusB4:Boolean = false

    var statusC1:Boolean = false
    var statusC2:Boolean = false
    var statusC3:Boolean = false
    var statusC4:Boolean = false

    var statusD1:Boolean = false
    var statusD2:Boolean = false
    var statusD3:Boolean = false
    var statusD4:Boolean = false

    var total:Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val data = intent.getParcelableExtra<Film>("data")
        tv_judul_bangku.text = data?.judul

        a1.setOnClickListener(this)
        a2.setOnClickListener(this)
        a3.setOnClickListener(this)
        a4.setOnClickListener(this)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)

        c1.setOnClickListener(this)
        c2.setOnClickListener(this)
        c3.setOnClickListener(this)
        c4.setOnClickListener(this)

        d1.setOnClickListener(this)
        d2.setOnClickListener(this)
        d3.setOnClickListener(this)
        d4.setOnClickListener(this)

        btn_beli_tiket.setOnClickListener {
            var intent = Intent(this@ChooseActivity, CheckoutActivity::class.java).putExtra("data",dataList)
            startActivity(intent)
        }
    }

    private fun beliTiket(total: Int) {
        if (total == 0){
            btn_beli_tiket.setText("Beli Tiket")
            btn_beli_tiket.visibility = INVISIBLE
        }else{
            btn_beli_tiket.setText("Beli Tiket ($total)")
            btn_beli_tiket.visibility = VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.a1 ->{
                if (statusA1){
                    a1.setImageResource(R.drawable.ic_empty)
                    statusA1 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    a1.setImageResource(R.drawable.ic_selected)
                    statusA1 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("A1", "7000")
                    dataList.add(data)
                }
            }
            R.id.a2 ->{
                if (statusA2){
                    a2.setImageResource(R.drawable.ic_empty)
                    statusA2 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    a2.setImageResource(R.drawable.ic_selected)
                    statusA2 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("A2", "7000")
                    dataList.add(data)
                }
            }
            R.id.a3 ->{
                if (statusA3){
                    a3.setImageResource(R.drawable.ic_empty)
                    statusA3 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    a3.setImageResource(R.drawable.ic_selected)
                    statusA3 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("A3", "7000")
                    dataList.add(data)
                }
            }
            R.id.a4 ->{
                if (statusA4){
                    a4.setImageResource(R.drawable.ic_empty)
                    statusA4 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    a4.setImageResource(R.drawable.ic_selected)
                    statusA4 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("A4", "7000")
                    dataList.add(data)
                }
            }

            R.id.b1 ->{
                if (statusB1){
                    b1.setImageResource(R.drawable.ic_empty)
                    statusB1 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    b1.setImageResource(R.drawable.ic_selected)
                    statusB1 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("B1", "7000")
                    dataList.add(data)
                }
            }
            R.id.b2 ->{
                if (statusB2){
                    b2.setImageResource(R.drawable.ic_empty)
                    statusB2= false
                    total -= 1
                    beliTiket(total)
                }else{
                    b2.setImageResource(R.drawable.ic_selected)
                    statusB2 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("B2", "7000")
                    dataList.add(data)
                }
            }
            R.id.b3 ->{
                if (statusB3){
                    b3.setImageResource(R.drawable.ic_empty)
                    statusB3 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    b3.setImageResource(R.drawable.ic_selected)
                    statusB3 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("B3", "7000")
                    dataList.add(data)
                }
            }
            R.id.b4 ->{
                if (statusB4){
                    b4.setImageResource(R.drawable.ic_empty)
                    statusB4 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    b4.setImageResource(R.drawable.ic_selected)
                    statusB4 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("B4", "7000")
                    dataList.add(data)
                }
            }

            R.id.c1 ->{
                if (statusC1){
                    c1.setImageResource(R.drawable.ic_empty)
                    statusC1 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    c1.setImageResource(R.drawable.ic_selected)
                    statusC1 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("C1", "7000")
                    dataList.add(data)
                }
            }
            R.id.c2 ->{
                if (statusC2){
                    c2.setImageResource(R.drawable.ic_empty)
                    statusC2 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    c2.setImageResource(R.drawable.ic_selected)
                    statusC2 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("C2", "7000")
                    dataList.add(data)
                }
            }
            R.id.c3 ->{
                if (statusC3){
                    c3.setImageResource(R.drawable.ic_empty)
                    statusC3 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    c3.setImageResource(R.drawable.ic_selected)
                    statusC3 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("C3", "7000")
                    dataList.add(data)
                }
            }
            R.id.c4 ->{
                if (statusC4){
                    c4.setImageResource(R.drawable.ic_empty)
                    statusC4 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    c4.setImageResource(R.drawable.ic_selected)
                    statusC4 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("C4", "7000")
                    dataList.add(data)
                }
            }

            R.id.d1 ->{
                if (statusD1){
                    d1.setImageResource(R.drawable.ic_empty)
                    statusD1 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    d1.setImageResource(R.drawable.ic_selected)
                    statusD1 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("D1", "7000")
                    dataList.add(data)
                }
            }
            R.id.d2 ->{
                if (statusD2){
                    d2.setImageResource(R.drawable.ic_empty)
                    statusD2 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    d2.setImageResource(R.drawable.ic_selected)
                    statusD2 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("D2", "7000")
                    dataList.add(data)
                }
            }
            R.id.d3 ->{
                if (statusD3){
                    d3.setImageResource(R.drawable.ic_empty)
                    statusD3 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    d3.setImageResource(R.drawable.ic_selected)
                    statusD3 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("D3", "7000")
                    dataList.add(data)
                }
            }
            R.id.d4 ->{
                if (statusD4){
                    d4.setImageResource(R.drawable.ic_empty)
                    statusD4 = false
                    total -= 1
                    beliTiket(total)
                }else{
                    d4.setImageResource(R.drawable.ic_selected)
                    statusD4 = true
                    total += 1
                    beliTiket(total)

                    val data = Checkout("D4", "7000")
                    dataList.add(data)
                }
            }
        }
    }
}