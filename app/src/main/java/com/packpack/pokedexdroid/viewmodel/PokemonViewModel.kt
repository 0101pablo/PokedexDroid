package com.packpack.pokedexdroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.packpack.pokedexdroid.api.PokemonRepository
import com.packpack.pokedexdroid.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread {
            loadPokemons()
        }.start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()
        pokemonsApiResult?.results?.let {

            pokemons.postValue(it.map { pokemonResult ->
                val id = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()
                val pokemonApiResult = PokemonRepository.getPokemon(id)
                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        },
                        pokemonApiResult.height,
                        pokemonApiResult.weight,
                        pokemonApiResult.stats.map { stat ->
                            stat.stat.name
                        },
                        pokemonApiResult.stats.map { stat ->
                            stat.base_stat
                        }
                    )
                }
            })
        }
    }
}