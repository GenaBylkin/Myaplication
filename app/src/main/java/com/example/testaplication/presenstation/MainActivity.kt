package com.example.testaplication.presenstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var llShoplist:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShoplist = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            showMyList(it)
        }
    }

    private fun showMyList(shopList: List<ShopList>){
        llShoplist.removeAllViews()
        for (item in shopList) {
            val layout = if (item.active){
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disable
            }
            val view = LayoutInflater.from(this).inflate(layout, llShoplist, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = item.name
            tvCount.text = item.quantity.toString()
            view.setOnLongClickListener {
                viewModel.editThisItem(item)
                true
            }
            llShoplist.addView(view)
        }
    }
}