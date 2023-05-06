package com.example.testaplication.domain

class GetShopItemId (private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopListId: Int):ShopList{
        return shopListRepository.getShopItem(shopListId)
    }
}