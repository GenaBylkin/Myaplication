package com.example.testaplication.presenstation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class ShopListAdapter:RecyclerView.Adapter<ShopListAdapter.shopItemViewHolder>() {

    private val list = listOf<ShopList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disable, parent, false)
        return shopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: shopItemViewHolder, position: Int) {
        val gList = list[position]
        holder.tvName.text = gList.name
        holder.tvCount.text = gList.quantity.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class shopItemViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

}