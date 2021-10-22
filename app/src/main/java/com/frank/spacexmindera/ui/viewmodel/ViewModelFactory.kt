package com.frank.spacexmindera.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frank.spacexmindera.data.api.RFHelper
import com.frank.spacexmindera.data.repository.Repository
import java.lang.RuntimeException

class ViewModelFactory(private val rfHelper: RFHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(Repository(rfHelper)) as T
        }
        throw RuntimeException()
    }
}