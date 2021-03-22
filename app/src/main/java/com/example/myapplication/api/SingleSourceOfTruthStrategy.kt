package com.example.myapplication.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T, A> getLiveData(
    databaseQuery: () -> LiveData<T>?,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        Log.i("Test","Firing database query")
        val source = databaseQuery.invoke()?.map {
            Log.i("Test","mapping database query data")
            Result.success(it)
        }
        Log.i("Test","database query fired")
        emitSource(source!!)

        Log.i("Test","making network call")
        val responseStatus = networkCall.invoke()
        Log.i("Test","network call finished")
        if (responseStatus.status == Result.Status.SUCCESS) {
            Log.i("Test","Inserting data to database")
            saveCallResult(responseStatus.data!!)
            Log.i("Test","records inserted into database")
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }
fun <A> postData(
        networkCall: suspend () -> LiveData<Result<A>>,
): LiveData<Result<A>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<A>())
            val responseStatus = networkCall.invoke()
            if (responseStatus.value?.status == Result.Status.SUCCESS) {
                emitSource(responseStatus)
            } else if (responseStatus.value?.status == Result.Status.ERROR) {
                emit(Result.error<A>(responseStatus.value?.message!!))
                //emitSource(source)
            }
        }

fun <A> postLiveData(
    networkCall: suspend () -> LiveData<Result<A>>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<A>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<A>())

        val responseStatus = networkCall.invoke()
        if (responseStatus.value?.status == Result.Status.SUCCESS) {
            saveCallResult(responseStatus.value?.data!!)
            emitSource(responseStatus)
        } else if (responseStatus.value?.status == Result.Status.ERROR) {
            emit(Result.error<A>(responseStatus.value?.message!!))
            //emitSource(source)
        }
    }

fun <A> deleteLiveData(
    networkCall: suspend () -> LiveData<Result<A>>,
    saveCallResult: suspend () -> Unit
): LiveData<Result<A>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<A>())
        /*val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)*/

        val responseStatus = networkCall.invoke()
        if (responseStatus.value?.status == Result.Status.SUCCESS) {
            //saveCallResult(responseStatus.value?.data!!)
            //databaseQuery.invoke()
            saveCallResult()
            emitSource(responseStatus)


        } else if (responseStatus.value?.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.value?.message!!))
            emitSource(responseStatus)
        }
    }

fun <T> getData(
        databaseQuery: () -> String
): LiveData<Result<String>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<String>())
            val source = databaseQuery.invoke().map {Result.success(it)}
        }



fun <T> getLiveData(
    databaseQuery: () -> LiveData<T>
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        val source = databaseQuery.invoke().map {Result.success(it)}
        emitSource(source)
    }

fun <A> resultPostToken(
    networkCall: suspend () -> LiveData<Result<A>>
): LiveData<Result<A>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.value?.status == Result.Status.SUCCESS) {
            //saveCallResult(responseStatus.value?.data!!)
            //databaseQuery.invoke()
            //saveCallResult()
            emitSource(responseStatus)
        } else if (responseStatus.value?.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.value?.message!!))
            //emitSource(source)
        }
    }
