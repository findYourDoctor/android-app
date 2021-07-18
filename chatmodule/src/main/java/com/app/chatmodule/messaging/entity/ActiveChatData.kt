package com.app.chatmodule.messaging.entity

data class ActiveChatData(
    var message : String = "",
    var name: String = "",
    var userId: String = "",
    var imageUrl: String = "",
    var timestamp : Long = 0

)
