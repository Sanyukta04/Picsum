package com.example.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity(tableName = "image")
data class ImageEntity(

        @field:SerializedName("id")
        @PrimaryKey
        @ColumnInfo(name = "id") var id: Long,

        @field:SerializedName("format")
        var format: String = "",

        @field:SerializedName("width")
        var width: Long = 0,

        @field:SerializedName("height")
        var height: Long = 0,

        @field:SerializedName("filename")
        var filename: String = "",

        @field:SerializedName("author")
        var author:  String = "",

        @field:SerializedName("author_url")
        var author_url: String = "",

        @field:SerializedName("post_url")
        var post_url: String = "",

)