package com.yrabdelrhmn.chatproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Groups(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val userGroup: List<String> = arrayListOf()

) : Parcelable