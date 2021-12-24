package com.yrabdelrhmn.chatproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val userName: String = "",
    val image: Int = 1,

) : Parcelable
