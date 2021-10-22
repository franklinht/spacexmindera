package com.frank.spacexmindera.data.model

data class LaunchInfo (
    val flightNumber : Int,
    val mission_name : String,
    val launch_success : Boolean?, // this variable is comming NULL from Server <<<<<<
    val launch_year : String,
    val launch_date_unix : Int,
    val launch_date_utc : String,
    val launch_date_local : String,
    val rocket : Rocket,
    val links : Links,
    val details : String
)