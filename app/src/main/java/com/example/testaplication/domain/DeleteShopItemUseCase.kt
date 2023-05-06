package com.example.testaplication.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteThisItem(shopItem: ShopList){
        shopListRepository.deleteThisItem(shopItem)
    }
}