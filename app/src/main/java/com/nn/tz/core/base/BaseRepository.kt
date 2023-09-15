package com.nn.tz.core.base

import com.nn.tz.core.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(request()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "unknow error"))
        }
    }.flowOn(Dispatchers.IO)   
    
    protected fun <T> doRequestList(request: suspend () -> List<T>) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(request()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "unknow error"))
        }
    }.flowOn(Dispatchers.IO)

}