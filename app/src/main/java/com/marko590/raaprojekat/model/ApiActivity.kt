package com.marko590.raaprojekat.model

data class ApiActivity(
    var activity:String,
    val type:String,
    val participants:Int,
    val price:Float,
    val link:String,
    val key:Int,
    val accessibility:Float
)
