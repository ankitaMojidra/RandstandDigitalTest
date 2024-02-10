package com.example.randstanddigitaltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randstanddigitaltest.data.repository.CountryRepository
import com.example.randstanddigitaltest.domain.NetworkStatus
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

class MainActivityViewModel(private val repository: CountryRepository) : ViewModel() {

    fun getCountryList() = liveData(Dispatchers.IO) {
        emit(NetworkStatus.Loading)
        try {
            val response = repository.getCountryList()
            emit(NetworkStatus.Success(response))
        } catch (e: IOException) {
            emit(NetworkStatus.Error("Network Error: ${e.message}"))
        } catch (e: HttpException) {
            emit(NetworkStatus.Error("Server Error: ${e.message}"))
        } catch (e: Exception) {
            emit(NetworkStatus.Error("Unknown Error: ${e.message}"))
        }
    }
}