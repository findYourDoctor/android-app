package com.abcd.findyourdoctor.dashboard.ui.notifications

data class ActiveChatData(
    var message : String = "",
    var members: ArrayList<String>,
    var timestamp : Long = 0

)
