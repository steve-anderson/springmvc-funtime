/*
  File: PlayerWithLinks

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.rest

import net.swahome.example.springmvc.funtime.model.Player
import java.net.URL

data class RestPlayer(val player: Player, val links: Links) {
    data class Links(val view: URL, val games: URL)

    constructor(player: Player, baseUrl: URL): this(player, buildLinks(player, baseUrl))

    companion object {
        private fun buildLinks(player: Player, baseUrl: URL): Links =
            Links(
                view = URL(baseUrl, "../player/${player.id}"),
                games = URL(baseUrl, "../game/list?playerId=${player.id}")
            )
    }
}
