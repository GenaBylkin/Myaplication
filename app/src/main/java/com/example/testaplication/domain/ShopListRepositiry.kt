package com.example.testaplication.domain

interface ShopListRepositiry {

    fun addNewItem(ShopItem:ShopList)

    fun deleteThisItem(ShopItem: ShopList)

    fun editItem(ShopItem: ShopList)

    fun getShopItem(shopListId: Int):ShopList

    fun getShopList():List<ShopList>
}