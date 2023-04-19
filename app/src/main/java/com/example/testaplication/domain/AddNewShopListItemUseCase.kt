package com.example.testaplication.domain

class AddNewShopListItemUseCase(private val shopListRepository: ShopListRepositiry) {

    fun addNewItem(shopItem:ShopList) {
        shopListRepository.addNewItem(shopItem)
    }
}