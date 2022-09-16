package com.example.shoeshop.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class Shoe(
    var company: String,
    var name: String,
    var size:String,
    var description:String
)