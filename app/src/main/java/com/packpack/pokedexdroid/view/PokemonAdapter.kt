package com.packpack.pokedexdroid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packpack.pokedexdroid.databinding.PokemonItemBinding
import com.packpack.pokedexdroid.domain.Pokemon

class PokemonAdapter(private val pokemonList: List<Pokemon?>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemBinding =
            PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(itemBinding)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.bindView(pokemon)
    }

    class PokemonViewHolder(private val itemBinding: PokemonItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(pokemon: Pokemon?) = with(itemView) {
            pokemon?.let { pokemon ->
                Glide.with(itemView.context).load(pokemon.imageUrl).into(itemBinding.ivPokemonImage)
                val idText = "Nº ${pokemon.formattedId}"
                itemBinding.tvPokemonId.text = idText
                itemBinding.tvPokemonName.text = pokemon.formattedName
                itemBinding.tvType1.text = pokemon.types[0].name.replaceFirstChar { it.uppercase() }
                if (pokemon.types.size > 1) {
                    itemBinding.tvType2.visibility = View.VISIBLE
                    itemBinding.tvType2.text =
                        pokemon.types[1].name.replaceFirstChar { it.uppercase() }
                } else {
                    itemBinding.tvType2.visibility = View.GONE
                }
            }
        }
    }
}