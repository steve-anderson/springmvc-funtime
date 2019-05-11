/*
  File: PlayerWithLinks

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.rest

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.swahome.example.springmvc.funtime.model.Player
import java.net.URL

@ApiModel(description = "A player in a game")
data class RestPlayer(
    @ApiModelProperty("The details for a player") val player: Player,
    @ApiModelProperty("Hyperlinks for a player") val links: Links
) {
    data class Links(
        @ApiModelProperty("Link to view this player") val view: URL,
        @ApiModelProperty("Link to games for this player") val games: URL
    )

    constructor(player: Player, baseUrl: URL): this(player, buildLinks(player, baseUrl))

    companion object {
        private fun buildLinks(player: Player, baseUrl: URL): Links =
            Links(
                view = URL(baseUrl, "../player/${player.id}"),
                games = URL(baseUrl, "../game/list?playerId=${player.id}")
            )
    }
}
