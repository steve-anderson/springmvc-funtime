/*
  File: Game

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.model


data class Game(val name: String, val player: Player, val opponent: Player, val won: Boolean)
