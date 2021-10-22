package com.frank.spacexmindera.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.frank.spacexmindera.data.api.Response
import com.frank.spacexmindera.data.repository.Repository
import kotlinx.coroutines.Dispatchers

class HomeViewModel (private val repository: Repository) : ViewModel() {

    //get company info
    fun getCompanyInfo() = liveData(Dispatchers.IO) {
        emit(Response.loading(data = null))

        try {
            emit(Response.success(data = repository.getCompanyInfo()))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Response.error(data = null))
        }
    }
    //get launch history
    fun getLaunchHistory() = liveData(Dispatchers.IO) {
        emit(Response.loading(data = null))

        try {
            emit(Response.success(data = repository.getLaunchHistory()))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Response.error(data = null))
        }
    }
}