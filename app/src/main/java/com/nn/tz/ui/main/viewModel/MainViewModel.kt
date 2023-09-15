package com.nn.tz.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nn.tz.core.base.BaseViewModel
import com.nn.tz.core.network.UIState
import com.nn.tz.data.model.Product
import com.nn.tz.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _getCategory = MutableStateFlow<UIState<List<Product>>>(UIState.Empty())
    val getCategory: StateFlow<UIState<List<Product>>> = _getCategory

    private val _getCategories = MutableStateFlow<UIState<List<String>>>(UIState.Empty())
    val getCategories: StateFlow<UIState<List<String>>> = _getCategories

    private val _changeCategories = MutableLiveData<String>("jewelery")
    val changeCategories : LiveData<String> = _changeCategories


    fun changeCategories(category: String){
        _changeCategories.value = category
    }

    fun getCategory(category:String,sort:String?) {
        repository.getCategory(category,sort).collectData(_getCategory)
    }

    fun getCategories() {
        repository.getCategories().collectData(_getCategories)
    }

}