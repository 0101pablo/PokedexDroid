package com.packpack.pokedexdroid.domain

import java.io.Serializable

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val height: Float,
    val weight: Float,
    val baseStatsNames: List<String>,
    val baseStatsValues: List<Int>
) : Serializable {
    val formattedId = id.toString().padStart(3, '0')
    val formattedName = name.replaceFirstChar { it.uppercase() }

    val formattedHeight = "Height: ${height / 10} m"
    val formattedWeight = "Weight: ${weight / 10} kgs"

    val formattedHpStat = "${baseStatsNames[0].uppercase()}: ${baseStatsValues[0]}"
    val formattedAtkStat =
        "${baseStatsNames[1].replaceFirstChar { it.uppercase() }}: ${baseStatsValues[1]}"
    val formattedDefStat =
        "${baseStatsNames[2].replaceFirstChar { it.uppercase() }}: ${baseStatsValues[2]}"
    fun formattedSpAtkStat():String {
        val words = baseStatsNames[3].split("-")
        return "${words[0].replaceFirstChar { it.uppercase() }} ${words[1].replaceFirstChar { it.uppercase() }}: ${baseStatsValues[3]}"
    }
    fun formattedSpDefStat(): String {
        val words = baseStatsNames[4].split("-")
        return "${words[0].replaceFirstChar { it.uppercase() }} ${words[1].replaceFirstChar { it.uppercase() }}: ${baseStatsValues[4]}"
    }
    val formattedSpeedStat =
        "${baseStatsNames[5].replaceFirstChar { it.uppercase() }}: ${baseStatsValues[5]}"

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedId}.png"
}