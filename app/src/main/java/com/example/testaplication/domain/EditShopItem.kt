package com.example.testaplication.domain

class EditShopItem (private val shopListRepository: ShopListRepositiry){

    fun editItem(shopItem: ShopList){
        shopListRepository.editItem(shopItem)
    }
}