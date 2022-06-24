package com.packpack.pokedexdroid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packpack.pokedexdroid.databinding.PokemonItemBinding
import com.packpack.pokedexdroid.domain.Pokemon
import com.packpack.pokedexdroid.utils.TypeUtils

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
        fun bindView(pokemon: Pokemon?) {
            val context = itemView.context
            pokemon?.let { pkm ->
                with(itemBinding) {
                    Glide.with(context).load(pkm.imageUrl).into(ivPokemonImage)
                    val idText = "Nº ${pkm.formattedId}"
                    tvPokemonId.text = idText
                    tvPokemonName.text = pkm.formattedName

                    for (i in pkm.types.indices) {
                        val pkmTypeName = pkm.types[i].name

                        when (i) {
                            0 -> {
                                tvType1.background =
                                    TypeUtils(context, pkmTypeName).getBgDrawable()
                                TypeUtils(context, pkmTypeName).getBgTint()?.let {
                                    tvType1.background.setTint(it)
                                }
                                tvType1.text = pkmTypeName.replaceFirstChar { it.uppercase() }
                                tvType1.setTextColor(TypeUtils(context, pkmTypeName).getTextColor())
                            }
                            1 -> {
                                tvType2.background =
                                    TypeUtils(context, pkmTypeName).getBgDrawable()
                                TypeUtils(context, pkmTypeName).getBgTint()?.let {
                                    tvType2.background.setTint(it)
                                }
                                tvType2.text = pkmTypeName.replaceFirstChar { it.uppercase() }
                                tvType2.setTextColor(TypeUtils(context, pkmTypeName).getTextColor())
                            }
                        }
                    }

                    if (pkm.types.size <= 1) {
                        tvType2.visibility = View.GONE
                    }
                    itemView.setOnClickListener {
                        Toast.makeText(context, "Clicked on ${pokemon.name}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}