/*
  File: PlayerDao

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.dao

import mu.KotlinLogging
import net.swahome.example.springmvc.funtime.model.Player
import org.springframework.stereotype.Repository

private val logger = KotlinLogging.logger {}

@Repository
open class PlayerDao {
    private var idSequence = 0L
    private val players = mutableMapOf<Long, Player>()
    private val emails = mutableSetOf<String>()

    @Synchronized
    fun write(player: Player): Player {
        logger.debug { "Writing $player" }

        ensureUniqueEmail(player.email)

        return if (player.id == null) {
            insert(player)
        } else {
            update(player)
            player
        }
    }

    @Synchronized
    fun read(id: Long): Player? {
        val found = players.get(id)

        logger.debug { "Find for id: $id, $found" }

        return found
    }

    @Synchronized
    fun readAll(): List<Player> {
        val result = players.map { it.value }

        logger.debug { "Read all players size: ${result.size}" }

        return result
    }

    private fun ensureUniqueEmail(email: String) {
        if (emails.contains(email)) {
            throw DuplicateEmailException("A player already exists for the email address, $email.")
        }
    }

    private fun insert(player: Player): Player {
        val id = ++idSequence
        val result = player.copy(id = id)

        players[id] = result
        emails.add(result.email)

        return result
    }

    private fun update(player: Player) {
        players[player.id ?: throw IllegalArgumentException("Failed to update player missing id. $player")] = player
    }
}
