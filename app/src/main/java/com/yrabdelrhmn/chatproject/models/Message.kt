package com.yrabdelrhmn.chatproject.models

data class Message(
    val messageContent: String = "",
    val senderName: String = "",
    val roomName: String = "",
    var type: Int = 0
)
data class SendMessage(val senderName: String, val messageContent: String)
data class initialData(val userName: String)
