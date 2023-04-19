package com.example.testaplication.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepositiry) {
    fun deleteThisItem(shopItem: ShopList){
        shopListRepository.deleteThisItem(shopItem)
    }
}