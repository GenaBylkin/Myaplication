package com.example.testaplication.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addNewItem(shopItem:ShopList)

    fun deleteThisItem(shopItem: ShopList)

    fun editItem(shopItem: ShopList)

    fun getShopItem(shopListId: Int):ShopList

    fun getShopList():LiveData<List<ShopList>>
}