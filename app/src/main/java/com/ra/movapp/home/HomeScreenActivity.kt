package com.ra.movapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ra.movapp.R
import com.ra.movapp.home.dashboard.DashboardFragment
import com.ra.movapp.home.settings.SettingFragment
import com.ra.movapp.home.ticket.TicketFragment
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val fragmentHome = DashboardFragment()
        val fragmentSetting = SettingFragment()
        val fragmentTicket = TicketFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1,R.drawable.ic_home_active)
            changeIcon(iv_menu2,R.drawable.ic_tiket)
            changeIcon(iv_menu3,R.drawable.ic_profile)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentTicket)

            changeIcon(iv_menu1,R.drawable.ic_home)
            changeIcon(iv_menu2,R.drawable.ic_tiket_active)
            changeIcon(iv_menu3,R.drawable.ic_profile)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(iv_menu1,R.drawable.ic_home)
            changeIcon(iv_menu2,R.drawable.ic_tiket)
            changeIcon(iv_menu3,R.drawable.ic_profile_active)
        }
    }


    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView,int: Int){
        imageView.setImageResource(int)
    }
}