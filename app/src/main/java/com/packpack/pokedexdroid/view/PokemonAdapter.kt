package com.packpack.pokedexdroid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packpack.pokedexdroid.R
import com.packpack.pokedexdroid.domain.Pokemon

class PokemonAdapter(private val pokemonList: List<Pokemon?>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.bindView(pokemon)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: Pokemon?) = with(itemView) {
            val ivPokemonImage = findViewById<ImageView>(R.id.ivPokemonImage)
            val tvPokemonId = findViewById<TextView>(R.id.tvPokemonId)
            val tvPokemonName = findViewById<TextView>(R.id.tvPokemonName)
            val tvPokemonType1 = findViewById<TextView>(R.id.tvType1)
            val tvPokemonType2 = findViewById<TextView>(R.id.tvType2)

            pokemon?.let { pokemon ->
                Glide.with(itemView.context).load(pokemon.imageUrl).into(ivPokemonImage)
                val idText = "Nº ${pokemon.formattedId}"
                tvPokemonId.text = idText
                tvPokemonName.text = pokemon.formattedName
                tvPokemonType1.text = pokemon.types[0].name.replaceFirstChar { it.uppercase() }
                if (pokemon.types.size > 1) {
                    tvPokemonType2.visibility = View.VISIBLE
                    tvPokemonType2.text = pokemon.types[1].name.replaceFirstChar { it.uppercase() }
                } else {
                    tvPokemonType2.visibility = View.GONE
                }
            }
        }
    }
}