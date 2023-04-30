package com.example.testaplication.presenstation

import androidx.recyclerview.widget.DiffUtil
import com.example.testaplication.domain.ShopList

class ShopListDiffCollback(
    private val oldList: List<ShopList>,
    private val newList: List<ShopList>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        return oldElement == newElement
    }
}