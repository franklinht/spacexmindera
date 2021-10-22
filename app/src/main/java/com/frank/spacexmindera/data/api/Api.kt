package com.frank.spacexmindera.data.api

import com.frank.spacexmindera.data.model.CompanyInfo
import com.frank.spacexmindera.data.model.LaunchInfo
import retrofit2.http.GET

interface Api {
    @GET("v3/info")
    suspend fun getCompanyInfo(): CompanyInfo

    @GET("v3/launches")
    suspend fun getLaunchHistory(): List<LaunchInfo>
}