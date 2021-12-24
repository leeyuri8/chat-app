package com.yrabdelrhmn.chatproject.models

enum class MessageType(val index: Int) {
    CHAT_SENDER(0), CHAT_RECEIVER(1), USER_JOIN(2), USER_LEAVE(3);
}
