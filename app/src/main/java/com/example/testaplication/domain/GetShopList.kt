package com.example.testaplication.domain

import androidx.lifecycle.LiveData

class GetShopList (private val shopListRepository: ShopListRepositiry){

    fun getShopList():LiveData<List<ShopList>>{
        return shopListRepository.getShopList()
    }
}