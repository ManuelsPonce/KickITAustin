package com.example.kickitaustin

import java.io.Serializable

data class GamePost (

    var location: String? = null,
    var profilePic: String? = null, //pictureUUID
    var gameOwner: String? = null,
    var startTime: String? = null,
    var numberOfPlayers: Int? = null,
    var levelOflay: String? = null,
    var dateOfGame:String? = null,
    var listOfAttendents: MutableList<String>? = null,

    //firebase stuff
    var rowId: String = "",
    var ownerUid: String? = null
   // @ServerTimestamp val timeStamp: Timestamp? = null
    ) : Serializable{

}