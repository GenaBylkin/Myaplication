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
import androidx.recyclerview.widget.RecyclerView
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            shopListAdapter.myList = it
        }
    }


    private fun setupRecyclerView(){
        val rvShop = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShop){
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ELEMENT_ENABLED,
                ShopListAdapter.MAX_POOL_VIEW
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ELEMENT_DISABLE,
                ShopListAdapter.MAX_POOL_VIEW
            )}

    }
}