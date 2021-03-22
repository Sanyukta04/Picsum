package com.example.myapplication.repository

import androidx.lifecycle.distinctUntilChanged
import com.example.myapplication.api.ImageApiClient
import com.example.myapplication.api.getLiveData
import com.example.myapplication.api.resultPostToken
import com.example.myapplication.db.dao.ImageDao
import java.util.Date
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageDb: ImageDao,
    private val imageApi: ImageApiClient
) {


    fun getImageData() = getLiveData(
                databaseQuery = { imageDb.getAll() },
                networkCall = { imageApi.fetchImageData() },
                saveCallResult = { imageDb.insertAll(it) }).distinctUntilChanged()

    fun getImage() = resultPostToken(
                networkCall = { imageApi.fetchImage() }).distinctUntilChanged()
}

