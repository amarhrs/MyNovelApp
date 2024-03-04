package com.amar.mynovelapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Novel(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
