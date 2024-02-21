package com.example.chatapplication

data class BaseModel(
    var id : Int?= 0,
    var name : String?= "",
    var address : String?= ""
){
    override fun toString(): String {
        return "$name $id"
    }
}