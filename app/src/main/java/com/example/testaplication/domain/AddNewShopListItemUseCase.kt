package com.example.testaplication.domain

class AddNewShopListItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addNewItem(shopItem:ShopList) {
        shopListRepository.addNewItem(shopItem)
    }
}