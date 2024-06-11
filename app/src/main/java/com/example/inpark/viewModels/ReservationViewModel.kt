package com.example.inpark.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationResponse
import com.example.inpark.data.model.User
import com.example.inpark.repository.ConnectivityRepository
import com.example.inpark.repository.ParkingRepository
import com.example.inpark.repository.ReservationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReservationViewModel(private val reservationRepository: ReservationRepository, private val connectivityRepository: ConnectivityRepository): ViewModel() {

    private val _createReservationResponse = MutableLiveData<Response<Reservation>?>()
    val createReservationResponse : LiveData<Response<Reservation>?> get() = _createReservationResponse

    private val _getAllReservationsResponse = MutableLiveData<Response<List<Reservation>>?>()

    val getAllReservationsResponse : LiveData<Response<List<Reservation>>?> get() = _getAllReservationsResponse


    private val _getReservationByUserResponse = MutableLiveData<Response<List<ReservationResponse>>?>()

    val getReservationByUserResponse : LiveData<Response<List<ReservationResponse>>?> get() = _getReservationByUserResponse

    private val _getReservationByUserFromCacheResponse = MutableLiveData<List<ReservationResponse>?>()

    val getReservationByUserFromCacheResponse : LiveData<List<ReservationResponse>?> get() = _getReservationByUserFromCacheResponse



    val isOnline = connectivityRepository.isConnected.asLiveData()

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

    fun getReservationByUserFromCache(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val reservations = reservationRepository.getReservationByUserFromCache(id)
                loading.value = false
                if (reservations != null) {
                    withContext(Dispatchers.Main) {
                        _getReservationByUserFromCacheResponse.value = reservations
                    }
                    Log.d("getfromCache", reservations.toString())
                } else {
                    error.value = "Failed to login user"
                }
            } catch (e: Exception) {
                Log.e("addUser(signUp)", "Database operation failed", e)
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
                Log.e("ReservationViewModel", "add reservation error", e)
                error.value = "Failed to add reservation: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun cacheReservation(reservation: Reservation,parking: Parking,parkingSlot: Place){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                reservationRepository.cacheReservation(reservation,parking,parkingSlot)
            } catch (e:Exception) {
                Log.e("AuthViewModel", "login user error", e)
                error.value = "Failed to login user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
    class Factory(private val reservationRepository: ReservationRepository, private val connectivityRepository: ConnectivityRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(reservationRepository, connectivityRepository) as T
        }
    }
}