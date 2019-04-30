/*
  File: GameController

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.web

import net.swahome.example.springmvc.funtime.model.Player
import net.swahome.example.springmvc.funtime.service.GameService
import net.swahome.example.springmvc.funtime.service.PlayerService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.inject.Inject

@Controller
@RequestMapping("/game")
open class GameController @Inject constructor(
    private val playerService: PlayerService,
    private val gameService: GameService
) {
    @RequestMapping(value = ["/list"], method = [RequestMethod.POST])
    fun getGamesForPlayerId(@ModelAttribute("playerRequest") playerRequest: Player, model: ModelMap): String {
        if (playerRequest.id != null) {
            val player = playerService.find(playerRequest.id!!)

            if (player != null) {
                val games = gameService.find(player.id!!)
                model.addAttribute("found", true)
                model.addAttribute("player", player)
                model.addAttribute("games", games)
            } else {
                model.addAttribute("found", false)
            }
        } else {
            model.addAttribute("found", false)
        }

        return "games"
    }
}
