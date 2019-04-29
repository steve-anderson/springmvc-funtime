/*
  File: PlayerService

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.service

import mu.KotlinLogging
import net.swahome.example.springmvc.funtime.dao.PlayerDao
import net.swahome.example.springmvc.funtime.model.Player
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.inject.Inject

private val logger = KotlinLogging.logger {}

@Service
open class PlayerService @Inject constructor (private val dao: PlayerDao) {

    @PostConstruct
    fun init() {
        logger.debug { "Write initial players." }
        dao.write(Player(null, "tom@example.com", "Tom", null, "Smith"))
        dao.write(Player(null, "joe@example.com", "Joe", null, "Green"))
        dao.write(Player(null, "mickey@example.com", "Mickey", "M", "Mouse"))
    }

    fun save(player: Player): Player {
        validate(player)
        return dao.write(player)
    }

    fun find(id: Long): Player? {
        return dao.read(id)
    }

    fun list(): List<Player> {
        return dao.readAll()
    }

    private fun validate(player: Player) {
        ensureValue(player.email) { "email is required. $player" }
        ensureValue(player.firstName) { "first name is required. $player" }
        ensureValue(player.lastName) { "last name is required. $player" }
    }

    private fun ensureValue(value: String?, msgProvider: () -> String) {
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException(msgProvider())
        }
    }
}
