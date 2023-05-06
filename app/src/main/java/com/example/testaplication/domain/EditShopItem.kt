package com.example.testaplication.domain

class EditShopItem (private val shopListRepository: ShopListRepository){

    fun editItem(shopItem: ShopList){
        shopListRepository.editItem(shopItem)
    }
}