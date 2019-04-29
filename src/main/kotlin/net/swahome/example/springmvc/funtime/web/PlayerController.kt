/*
  File: PlayerController

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.web

import net.swahome.example.springmvc.funtime.service.PlayerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.inject.Inject

@Controller
@RequestMapping("/player")
open class PlayerController @Inject constructor (private val playerService: PlayerService) {

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET])
    fun getPLayerById(@PathVariable id: Long, model: Model): String {
        val found = playerService.find(id)
        model.addAttribute("found", found != null)
        model.addAttribute("player", found)
        return "player"
    }

    @RequestMapping(path = ["/list"], method = [RequestMethod.GET])
    fun getPlayers(model: Model): String {
        val players = playerService.list()
        model.addAttribute("players", players)
        return "players"
    }
}
