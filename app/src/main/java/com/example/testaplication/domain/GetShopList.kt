package com.example.testaplication.domain

class GetShopList (private val shopListRepository: ShopListRepositiry){

    fun getShopList():List<ShopList>{
        return shopListRepository.getShopList()
    }
}