/*
  File: PlayerEndpoint

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.rest

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import net.swahome.example.springmvc.funtime.service.PlayerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URL
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

@Api(description = "Operations for a player or players of games")
@RestController
@RequestMapping("/api/player")
open class PlayerEndpoint @Inject constructor(val playerService: PlayerService) {
    @ApiOperation("Find a player by identifier")
    @GetMapping("/{id}")
    fun getPlayerById(
        @ApiParam("Player's identifier") @PathVariable id: Long,
        request: HttpServletRequest
    ): ResponseEntity<RestPlayer?> {
        val baseUrl = URL(request.requestURL.toString())
        val found = playerService.find(id)
        return if (found != null) {
            ResponseEntity.ok(RestPlayer(found, baseUrl))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ApiOperation("List all players")
    @GetMapping("/list")
    fun getPlayers(request: HttpServletRequest): List<RestPlayer> {
        val baseUrl = URL(request.requestURL.toString())
        val players = playerService.list()
        return players.map { RestPlayer(it, baseUrl) }
    }
}
