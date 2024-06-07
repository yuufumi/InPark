package com.example.inpark.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationWithPlaceAndParking
import com.example.inpark.data.model.User
import com.example.inpark.repository.ParkingRepository
import com.example.inpark.repository.ReservationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReservationViewModel(private val reservationRepository: ReservationRepository): ViewModel() {

    private val _createReservationResponse = MutableLiveData<Response<Reservation>?>()
    val createReservationResponse : LiveData<Response<Reservation>?> get() = _createReservationResponse

    private val _getAllReservationsResponse = MutableLiveData<Response<List<Reservation>>?>()

    val getAllReservationsResponse : LiveData<Response<List<Reservation>>?> get() = _getAllReservationsResponse


    private val _getReservationByUserResponse = MutableLiveData<Response<List<ReservationWithPlaceAndParking>>?>()

    val getReservationByUserResponse : LiveData<Response<List<ReservationWithPlaceAndParking>>?> get() = _getReservationByUserResponse

    val loading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun getAllReservations(){
        loading.value = true
        error.value = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = reservationRepository.getAllReservations()
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _getAllReservationsResponse.value = response
                    }
                    Log.d("AuthViewModel", "Register user success: ${response.body()}")
                } else {
                    error.value = "Failed to register user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "Register user error", e)
                error.value = "Failed to register user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun getReservationByUser(id:String){
        loading.value = true
        error.value = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = reservationRepository.getReservationByUser(id)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _getReservationByUserResponse.value = response
                    }
                    Log.d("AuthViewModel", "Register user success: ${response.body()}")
                } else {
                    error.value = "Failed to register user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "Register user error", e)
                error.value = "Failed to register user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
    fun createReservation(reservation: ReservationRequest) {
        loading.value = true
        error.value = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = reservationRepository.createReservation(reservation)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _createReservationResponse.value = response
                    }
                    Log.d("AuthViewModel", "Register user success: ${response.body()}")
                } else {
                    error.value = "Failed to register user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "Register user error", e)
                error.value = "Failed to register user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
    class Factory(private val reservationRepository: ReservationRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(reservationRepository) as T
        }
    }
}