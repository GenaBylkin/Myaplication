package com.example.testaplication.presenstation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class ShopListAdapter:RecyclerView.Adapter<ShopListAdapter.shopItemViewHolder>() {
    var count = 0
    var myList = listOf<ShopList>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder, count: ${++count}")
        val loyout = when(viewType){
            ELEMENT_ENABLED -> R.layout.item_shop_enabled
            ELEMENT_DISABLE -> R.layout.item_shop_disable
            else -> throw java.lang.RuntimeException("Unknown view type!")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            loyout,
            parent,
            false
        )
        return shopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: shopItemViewHolder, position: Int) {
        val gList = myList[position]
        holder.tvName.text = gList.name
        holder.tvCount.text = gList.quantity.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun onViewRecycled(holder: shopItemViewHolder) {
        super.onViewRecycled(holder)

    }

    override fun getItemCount(): Int = myList.size

    override fun getItemViewType(position: Int): Int {
        val element = myList[position]
        return if (element.active){
            ELEMENT_ENABLED
        } else {
            ELEMENT_DISABLE
        }
    }

    class shopItemViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }


    companion object{
        const val ELEMENT_ENABLED = 1
        const val ELEMENT_DISABLE = 0
        const val MAX_POOL_VIEW = 20
    }
}