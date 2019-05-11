/*
  File: GameEndpoint

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.rest

import io.swagger.annotations.*
import net.swahome.example.springmvc.funtime.service.GameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URL
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

@ApiModel(description = "A game between a player and an opponent that is won or lost.")
data class RestGame(
    @ApiModelProperty("Game's name") val name: String,
    @ApiModelProperty("The player") val player: RestPlayer,
    @ApiModelProperty("The opponent") val opponent: RestPlayer,
    @ApiModelProperty("Did the player win?") val won: Boolean
)

@Api(description = "Operations for player games")
@RestController
@RequestMapping("/api/game")
open class GameEndpoint @Inject constructor(
    val gameService: GameService
) {
    @ApiOperation("List the games for a player")
    @GetMapping("/list")
    fun getGamesForPlayerId(
        @ApiParam("Player's identifier") @RequestParam("playerId") playerId: Long,
        request: HttpServletRequest
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
