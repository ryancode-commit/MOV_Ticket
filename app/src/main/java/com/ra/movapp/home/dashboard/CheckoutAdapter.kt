package com.ra.movapp.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ra.movapp.R
import com.ra.movapp.model.Checkout
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener:(Checkout)-> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val tvSeat:TextView = view.findViewById(R.id.tv_seat)
        private val tvRincian:TextView = view.findViewById(R.id.tv_rincian)

        fun bindItem(data:Checkout, listener:(Checkout)-> Unit,context: Context){
           val localID = Locale("id","ID")
            val formatRupiah =  NumberFormat.getCurrencyInstance(localID)

            tvRincian.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")){
                tvSeat.setText(data.kursi)
                tvSeat.setCompoundDrawables(null,null,null,null)
            }else{
                tvSeat.setText("Seat No.${data.kursi}")
            }


            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
