package com.example.myapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.myapplication.db.entity.ImageEntity
import com.example.myapplication.repository.ImageRepository
import java.util.Date

class ImageViewModel @ViewModelInject constructor(private val imageRepository: ImageRepository) : ViewModel() {

    fun getImageData() = imageRepository.getImageData()

    //fun getImage() = imageRepository.getImage()

    //var imageEntity = ImageEntity()

}