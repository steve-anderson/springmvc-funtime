/*
  File: Player

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.model

data class Player(
    var id: Long? = null,
    var email: String = "",
    var firstName: String = "",
    var middleName: String? = null,
    var lastName: String = ""
)
