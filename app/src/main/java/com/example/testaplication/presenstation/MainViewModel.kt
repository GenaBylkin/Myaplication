package com.example.testaplication.presenstation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testaplication.data.ShopListRepositoryImpl
import com.example.testaplication.domain.DeleteShopItemUseCase
import com.example.testaplication.domain.EditShopItem
import com.example.testaplication.domain.GetShopList
import com.example.testaplication.domain.ShopList

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopList = GetShopList(repository)
    private val deleteShopItem = DeleteShopItemUseCase(repository)
    private val getEditShopItem = EditShopItem(repository)

    val shopList = getShopList.getShopList()

    fun deleteItem(shopItemList: ShopList){
        deleteShopItem.deleteThisItem(shopItemList)
    }

    fun editThisItem(shopItem: ShopList){
        val changeStat = shopItem.copy(active = !shopItem.active)
        getEditShopItem.editItem(changeStat)
    }

}