package com.nn.tz.ui.detail.viewModel

import com.nn.tz.core.base.BaseViewModel
import com.nn.tz.core.network.UIState
import com.nn.tz.data.model.Product
import com.nn.tz.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): BaseViewModel() {


    private val _getProduct = MutableStateFlow<UIState<Product>>(UIState.Empty())
    val getProduct: StateFlow<UIState<Product>> = _getProduct


    fun getProduct(id:Int?){
        repository.getProducts(id).collectData(_getProduct)
    }

}