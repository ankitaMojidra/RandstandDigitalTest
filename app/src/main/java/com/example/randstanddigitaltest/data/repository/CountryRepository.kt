package com.example.randstanddigitaltest.data.repository

import com.example.randstanddigitaltest.data.api.RetroInstance
import java.io.IOException

class CountryRepository {

    private val retroService = RetroInstance.apiService

    suspend fun getCountryList(): List<CountryModel> {
        return try {
            retroService.getCountryList()
        } catch (e: IOException) {
            throw IOException("Network Error")
        } catch (e: Exception) {
            throw Exception("Unknown Error")
        }
    }
}