package com.nn.tz.repository

import com.nn.tz.core.base.BaseRepository
import com.nn.tz.data.local.ProductDao
import com.nn.tz.data.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
) : BaseRepository() {

    fun getCategory(category: String, sort: String?) = doRequest {
        apiService.getProductsByCategory(category, sort)
    }

    fun getCategories() = doRequest {
        apiService.getCategories()
    }

    fun getProducts(id: Int?) = doRequest {
        apiService.getProducts(id)
    }

}