package com.example.mobile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mobile.model.MoneyItem
import java.util.Collections.addAll

class RepayViewModel() : ViewModel() {
    private var _allItems = MutableLiveData<List<MoneyItem>>()
    val allItems get() = _allItems

    fun updateItems(list: List<MoneyItem>) {
        _allItems.value = list
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                return super.create(modelClass)
//            }
//        }
//    }
}

class RepayViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        if (modelClass.isAssignableFrom(RepayViewModel::class.java)) {
            return RepayViewModel() as T
        }
        throw IllegalArgumentException("BBB")
    }
}