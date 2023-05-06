package com.example.testaplication.presenstation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList

class ShopListAdapter:ListAdapter<ShopList,ShopItemViewHolder>(ShopItemDiffutilCollback()) {
    var onLongClickListener:((ShopList) -> Unit)? = null
    var onClickListener:((ShopList) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
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
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val gList = getItem(position)
        holder.tvName.text = gList.name
        holder.tvCount.text = gList.quantity.toString()
        holder.view.setOnLongClickListener {
            onLongClickListener?.invoke(gList)
            true
        }

        holder.view.setOnClickListener {
            onClickListener?.invoke(gList)
            true
        }

    }

    override fun getItemViewType(position: Int): Int {
        val element = getItem(position)
        return if (element.active){
            ELEMENT_ENABLED
        } else {
            ELEMENT_DISABLE
        }
    }

    companion object{
        const val ELEMENT_ENABLED = 1
        const val ELEMENT_DISABLE = 0
        const val MAX_POOL_VIEW = 20
    }
}