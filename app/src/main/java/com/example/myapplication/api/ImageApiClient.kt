package com.example.myapplication.api

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ImageApiClient @Inject constructor(private val service: ImageService) : BaseDataSource() {

    suspend fun fetchImageData() = getResult {
        service.getAuthor()
    }

    suspend fun fetchImage() = MutableLiveData(getResult { service.getImage("300","300?image",4) })
}