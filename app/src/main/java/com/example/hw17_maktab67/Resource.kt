package com.example.hw17_maktab67

sealed class Resource<T> (val data: T? = null, val error: String? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(data: T? = null, error: String?): Resource<T>(data, error)
}