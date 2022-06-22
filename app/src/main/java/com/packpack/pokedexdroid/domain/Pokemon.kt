package com.packpack.pokedexdroid.domain

import java.util.*

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>
) {
    val formattedId = id.toString().padStart(3, '0')
    val formattedName = name.replaceFirstChar { it.uppercase() }

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedId}.png"
}