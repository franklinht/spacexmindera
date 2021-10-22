package com.frank.spacexmindera.data.repository

import com.frank.spacexmindera.data.api.RFHelper

class Repository(private val rfHelper: RFHelper) {
    suspend fun getCompanyInfo() = rfHelper.getCompanyInfo()
    suspend fun getLaunchHistory() = rfHelper.getLaunchHistory()
}