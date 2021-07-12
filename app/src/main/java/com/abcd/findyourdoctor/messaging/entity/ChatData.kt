package com.abcd.findyourdoctor.messaging.entity

import java.lang.reflect.Member

data class ChatData(
    var message : String = "",
    var senderId : String = "",
    var timestamp : Long = 0,
)
