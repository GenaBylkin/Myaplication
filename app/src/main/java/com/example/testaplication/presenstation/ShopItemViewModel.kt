package com.example.testaplication.presenstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testaplication.data.ShopListRepositoryImpl
import com.example.testaplication.domain.*

class ShopItemViewModel:ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemList = GetShopItemId(repository)
    private val editShopItem = EditShopItem(repository)
    private val addNewItem = AddNewShopListItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    private val _errorInputCount = MutableLiveData<Boolean>()
    private val _itemShopList = MutableLiveData<ShopList>()
    private val _closeScreen = MutableLiveData<Unit>()

    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    val itemShopList:LiveData<ShopList>
        get() = _itemShopList

    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    fun getShopList(shopItemId: Int) {
        val item = getShopItemList.getShopItem(shopItemId)
        _itemShopList.value = item
    }

    fun addNewShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)

        val correctValid = correctDataInput(name, count)
        if (correctValid){
            val shopItem = ShopList(name,count,true)
            addNewItem.addNewItem(shopItem)
            mayClosedScreen()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val correctValid = correctDataInput(name, count)
        if (correctValid){
           _itemShopList.value?.let {
               val item = it.copy(name = name, quantity = count)
               editShopItem.editItem(item)
               mayClosedScreen()
           }
        }
    }


    private fun parseName(inputName: String?) :String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?) : Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: java.lang.Exception){
            0
        }
    }

    private fun correctDataInput(name: String, count:Int) : Boolean {
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }

        return result
    }

    fun resetInputName() {
        _errorInputName.value = false
    }

    fun resetInputCount() {
        _errorInputCount.value = false
    }

    private fun mayClosedScreen() {
        _closeScreen.value = Unit
    }

}