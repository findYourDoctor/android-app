package com.app.chatmodule.messaging.entity

data class ChatData(
    var message : String = "",
    var senderId : String = "",
    var timestamp : Long = 0,
)
