package com.example.testaplication.domain

interface ShopListRepositiry {

    fun addNewItem(shopItem:ShopList)

    fun deleteThisItem(shopItem: ShopList)

    fun editItem(shopItem: ShopList)

    fun getShopItem(shopListId: Int):ShopList

    fun getShopList():List<ShopList>
}