/*
  File: GameService

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.service

import mu.KotlinLogging
import net.swahome.example.springmvc.funtime.model.Game
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.inject.Inject

private val logger = KotlinLogging.logger {}

@Service
open class GameService @Inject constructor(private val playerService: PlayerService) {
    private val playerIdToGame = mutableMapOf<Long, MutableList<Game>>()

    @PostConstruct
    fun init() {
        logger.debug { "Creating games." }

        val players = playerService.list()

        addGame(Game("Tennis", players[0], players[1], true))
        addGame(Game("Chess", players[0], players[1], false))
        addGame(Game("Ping Pong", players[0], players[1], false))

        addGame(Game("Tennis", players[0], players[2], true))
        addGame(Game("Chess", players[0], players[2], false))

        addGame(Game("Ping Pong", players[1], players[2], false))
    }

    fun find(playerId: Long): List<Game> {
        val result = playerIdToGame[playerId] ?: listOf<Game>()
        logger.debug { "Games size: ${result.size} for playerId: $playerId" }
        return result
    }

    private fun addGame(game: Game) {
        playerIdToGame.computeIfAbsent(game.player.id!!) { mutableListOf() }.add(game)
        playerIdToGame.computeIfAbsent(game.opponent.id!!) { mutableListOf() }.add(
            game.copy(player = game.opponent, opponent = game.player, won = !game.won)
        )
    }
}

