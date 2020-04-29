package com.example.kickitaustin

import java.sql.RowId
import java.sql.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class GamePost  (
    var location: String? = null,
    var profilePic: String? = null, //pictureUUID
    var gameOwner: String? = null,
    var startTime: String? = null,
    var numberOfPlayers: Int? = null,
    var levelOflay: String? = null,
    var dateOfGame:String? = null,

    //firebase stuff
    var rowId: String = "",
    var ownerUid: String? = null
   // @ServerTimestamp val timeStamp: Timestamp? = null
    ) {

}