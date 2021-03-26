package com.example.first_android.data.network;

data class ResponseData<T>(
    var status: String = "",
    var message: String = "",
    var results: T
)
