package com.example.inpark.data.api

object apiConstants {
    // Backend Base URL
    const val BASE_URL = "https://90a5-154-121-31-23.ngrok-free.app"
    // auth Endpoints
    const val REGISTER = "api/users/register"
    const val LOGIN = "api/users/login"
    const val GETUSERS = "api/users"
    const val GETBYEMAIL = "api/users/get_user/email"

    // parkings endpoints
    const val GETALLPARKINGS = "api/parkings"
    const val SEARCHPARKINGS = "api/parkings/search"
    const val PARKINGBYPLACE = "api/parkings/byplace"

    // reservations endpoints

    const val ALLRESERVATIONS = "api/reservations/"
    const val RESERVATIONSBYUSER = "api/reservations/user"
    const val ACTIVERESERVATIONS = "api/reservations/user/active"
    const val CREATERESERVATION = "api/reservations"

    // parking slots endpoints
    const val SLOTSBYPARKING = "api/places/parking"



}