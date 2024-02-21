package com.example.chatapplication

import java.util.Calendar

data class MessageDataClass(
    var id: Int = 0,
    var message : String ?= null,
    var dateTime : Calendar = Calendar.getInstance()
)
