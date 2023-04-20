package com.example.testaplication.data
import com.example.testaplication.domain.ShopList
import com.example.testaplication.domain.ShopListRepositiry

object ShopListRepositoryImpl: ShopListRepositiry {

    private val shopListItem = mutableListOf<ShopList>()
    private var autoID = 0

    override fun addNewItem(shopItem: ShopList) {
        if (shopItem.id == ShopList.UNDEFINED_ID){
            shopItem.id = autoID++
    }
        shopListItem.add(shopItem)
    }

    override fun deleteThisItem(shopItem: ShopList) {
        shopListItem.remove(shopItem)
    }

    override fun editItem(shopItem: ShopList) {
        val oldElement = getShopItem(shopItem.id)
        shopListItem.remove(oldElement)
        addNewItem(shopItem)
    }

    override fun getShopItem(shopListId: Int): ShopList {
        return shopListItem.find { it.id == shopListId } ?:
        throw java.lang.RuntimeException("This item $shopListItem don't found")
    }

    override fun getShopList(): List<ShopList> {
        return shopListItem
    }

}
