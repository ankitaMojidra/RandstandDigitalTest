package com.example.randstanddigitaltest.data.repository

data class CountryModel(
    val code: String,
    val name: String,
    val region: String,
    val capital: String,
    val language: Language,
    val currency: Currency,
    val flag: String
)

data class Language(
    val code: String? = "",
    val name: String? = ""
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String?
)
