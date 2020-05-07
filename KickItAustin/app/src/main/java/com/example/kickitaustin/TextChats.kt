package com.example.kickitaustin

import java.io.Serializable

data class TextChats (
    var gameOwner: String? = null,
    var message: String? = null,
    var ownerUid: String? = null
) : Serializable {
}