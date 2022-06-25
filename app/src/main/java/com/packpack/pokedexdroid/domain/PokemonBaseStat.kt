package com.packpack.pokedexdroid.domain

data class PokemonBaseStat(
    val base_stat: Int,
    val stat: PokemonStatInfo
)

data class PokemonStatInfo(
    val name: String
)