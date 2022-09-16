package com.example.shoeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeshop.data.model.Shoe

class MainViewModel : ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList
    private var _isSaveBTClicked = MutableLiveData<Boolean>()
    val isSaveBTClicked : LiveData<Boolean>
        get() = _isSaveBTClicked

    init {
        _shoeList.value = mutableListOf()
        addShoe("Pink Shoes", "5", "Link", "pink girly shoes")
        addShoe("Blue Shoes", "8", "Link", "blue shoes")
    }

    fun addShoe(name: String, size: String, company: String, description: String) {
        _shoeList.value?.add(Shoe(name = name, size = size, company = company, description = description))
        _isSaveBTClicked.value = true
        _isSaveBTClicked.value = false
    }

}