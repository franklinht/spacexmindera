package com.frank.spacexmindera.data.model

data class CompanyInfo (
    val name : String,
    val founder : String,
    val founded : Int,
    val employees : Int,
    val vehicles : Int,
    val launchSites : Int,
    val testSites : Int,
    val ceo : String,
    val cto : String,
    val coo : String,
    val ctoPropulsion : String,
    val valuation : Long,
    val headquarters : Headquarters,
    val summary : String
)