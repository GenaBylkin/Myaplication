package com.example.testaplication.data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testaplication.domain.ShopList
import com.example.testaplication.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListItemLD = MutableLiveData<List<ShopList>>()
    private val shopListItem = sortedSetOf<ShopList>({o1,o2 -> o1.id.compareTo(o2.id)})
    private var autoID = 0

    init {
        for (i in 0 until 1000){
            val item = ShopList("Name $i" ,i, Random.nextBoolean())
            addNewItem(item)
        }
    }

    override fun addNewItem(shopItem: ShopList) {
        if (shopItem.id == ShopList.UNDEFINED_ID){
            shopItem.id = autoID++
    }
        shopListItem.add(shopItem)
        updateList()
    }

    override fun deleteThisItem(shopItem: ShopList) {
        shopListItem.remove(shopItem)
        updateList()
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

    override fun getShopList(): LiveData<List<ShopList>> {
        return shopListItemLD
    }

    private fun updateList() {
        shopListItemLD.value = shopListItem.toList()
    }
}
