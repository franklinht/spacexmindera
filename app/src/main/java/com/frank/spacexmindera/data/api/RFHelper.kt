package com.frank.spacexmindera.data.api

class RFHelper(val api: Api) {
    suspend fun getCompanyInfo() = api.getCompanyInfo()
    suspend fun getLaunchHistory() = api.getLaunchHistory()
}