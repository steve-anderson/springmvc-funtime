/*
  File: PlayerServiceTest

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.service

import net.swahome.example.springmvc.funtime.config.TestConfig
import net.swahome.example.springmvc.funtime.model.Player
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import javax.inject.Inject

@ContextConfiguration(classes = [TestConfig::class])
class PlayerServiceTest: AbstractTestNGSpringContextTests() {
    @Inject
    private lateinit var service: PlayerService

    @Test
    fun savePlayer() {
        val player = Player(null, "savePlayer@test.com", "save", null, "Player")

        val actual = service.save(player)
        val expected = player.copy(id = actual.id)

        assertEquals(actual, expected)
    }

    @Test
    fun findPlayer() {
        val saved = service.save(Player(null, "findPlayer@test.com", "find", null, "Player"))

        val actual = service.find(saved.id!!)
        assertEquals(actual, saved)
    }

    @Test
    fun listPlayers() {
        val saved = service.save(Player(null, "listPlayer@test.com", "list", null, "Player"))

        val actual = service.list()
        assertTrue(actual.contains(saved))
    }
}
