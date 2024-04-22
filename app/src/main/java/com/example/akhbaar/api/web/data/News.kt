package com.example.akhbaar.api.web.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.net.URL
import java.util.Date

@Parcelize
data class News(
    val datePosted: String = "",
    val newsTitle: String = "",
    val newsDescription: String = "",
    val newsLink: String = "",
    val newsImage: String = "",
): Parcelable
