/*
  File: GameEndpoint

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.rest

import net.swahome.example.springmvc.funtime.service.GameService
import net.swahome.example.springmvc.funtime.service.PlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URL
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest


data class RestGame(
    val name: String, val player: RestPlayer, val opponent: RestPlayer, val won: Boolean
)

@RestController
@RequestMapping("/api/game")
open class GameEndpoint @Inject constructor(
    val playerService: PlayerService,
    val gameService: GameService
) {
    @GetMapping("/list")
    fun getGamesForPlayerId(
        @RequestParam("playerId") playerId: Long, request: HttpServletRequest
    ): List<RestGame> {
        val baseUrl = URL(request.requestURL.toString())

        val games = gameService.find(playerId)
        return games.map {
            RestGame(
                it.name,
                RestPlayer(it.player, baseUrl),
                RestPlayer(it.opponent, baseUrl),
                it.won
            )
        }
    }
}
