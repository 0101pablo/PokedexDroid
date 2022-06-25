package com.packpack.pokedexdroid.api.model

import com.packpack.pokedexdroid.domain.PokemonBaseStat
import com.packpack.pokedexdroid.domain.PokemonType

data class PokemonsApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>,
    val height: Float,
    val weight: Float,
    val stats: List<PokemonBaseStat>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)