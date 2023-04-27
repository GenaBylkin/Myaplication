package com.example.testaplication.presenstation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class ShopListAdapter:RecyclerView.Adapter<ShopListAdapter.shopItemViewHolder>() {

     var myList = listOf<ShopList>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disable,
            parent,
            false
        )
        return shopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: shopItemViewHolder, position: Int) {
        val gList = myList[position]

        holder.view.setOnLongClickListener {
            true
        }

        if (gList.active){
            holder.tvName.text = "${gList.name} "
            holder.tvCount.text = gList.quantity.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_red_dark))
        }
    }

    override fun onViewRecycled(holder: shopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.text = ""
        holder.tvCount.text = ""
        holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_red_dark))
    }

    override fun getItemCount(): Int = myList.size

    class shopItemViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

}