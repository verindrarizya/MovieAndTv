package com.example.movieandtv.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "first_air_date")
    var date: String,

    @ColumnInfo(name = "rating")
    var rate: Double,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)