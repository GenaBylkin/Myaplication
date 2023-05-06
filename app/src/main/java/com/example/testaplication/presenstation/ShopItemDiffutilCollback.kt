package com.example.testaplication.presenstation

import androidx.recyclerview.widget.DiffUtil
import com.example.testaplication.domain.ShopList

class ShopItemDiffutilCollback:DiffUtil.ItemCallback<ShopList>() {
    override fun areItemsTheSame(oldItem: ShopList, newItem: ShopList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopList, newItem: ShopList): Boolean {
        return oldItem == newItem
    }
}