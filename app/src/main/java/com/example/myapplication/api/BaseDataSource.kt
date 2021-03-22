package com.example.myapplication.api

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.e("Error","Network error",e)
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        //Timber.e(message)
        return Result.error("Network call has failed for a following reason: $message")
    }

    //protected fun getToken()="Bearer ${prefUtil.getString("TOKEN")}"
}
