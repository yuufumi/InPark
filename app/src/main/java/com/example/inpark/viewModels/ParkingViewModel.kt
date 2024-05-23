package com.example.inpark.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inpark.data.api.SearchRequest
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.model.User
import com.example.inpark.data.model.Parking
import com.example.inpark.repository.AuthRepository
import com.example.inpark.repository.ParkingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ParkingViewModel(private val parkingRepository: ParkingRepository): ViewModel() {

    private val _allParkingsResponse = MutableLiveData<Response<List<Parking>>>()


    val allParkingsResponse : LiveData<Response<List<Parking>>> get() = _allParkingsResponse

    private val _parkingByIdResponse = MutableLiveData<Response<Parking>>()

    val parkingByIdResponse : LiveData<Response<Parking>> get() = _parkingByIdResponse

    private val _searchParkingsResponse = MutableLiveData<Response<List<Parking>>>()

    val searchParkingsResponse: LiveData<Response<List<Parking>>> get() = _searchParkingsResponse
    val loading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun getAllParkings(){
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = parkingRepository.getAllParkings()
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _allParkingsResponse.value = response
                    }
                    Log.d("ParkingViewModel", "fetching parkings success: ${response.body()}")
                } else {
                    error.value = "Failed to fetch parkings: ${response.message()}"
                }

            } catch (e: Exception) {
                Log.e("ParkingViewModel", "fetch parkings error", e)
                error.value = "Failed to fetch parkings: ${e.message}"
            } finally {
                loading.value = false

            }
        }
    }

    fun getParkingById(id: String) {
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = parkingRepository.getParkingById(id)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _parkingByIdResponse.value = response
                    }
                    Log.d("ParkingViewModel", "fetching parking by id success: ${response.body()}")
                } else {
                    error.value = "Failed to fetch parking by id: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("ParkingViewModel", "fetch parking error", e)
                error.value = "Failed to fetch parking: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun searchParkings(query:SearchRequest){
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = parkingRepository.searchParkings(query)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _searchParkingsResponse.value = response
                    }
                    Log.d("ParkingViewModel", "Parkings search success: ${response.body()}")
                } else {
                    error.value = "Failed to search parkings: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("ParkingViewModel", "parking search error", e)
                error.value = "Failed to search parkings: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
    class Factory(private val parkingRepository: ParkingRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingViewModel(parkingRepository) as T
        }
    }
}