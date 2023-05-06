package com.example.testaplication.domain

import androidx.lifecycle.LiveData

class GetShopList (private val shopListRepository: ShopListRepository){

    fun getShopList():LiveData<List<ShopList>>{
        return shopListRepository.getShopList()
    }
}