package com.packpack.pokedexdroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.packpack.pokedexdroid.databinding.ActivityPokemonDetailsBinding
import com.packpack.pokedexdroid.domain.Pokemon
import com.packpack.pokedexdroid.utils.TypeUtils

class PokemonDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        with(binding) {
            val context = this@PokemonDetailsActivity
            Glide.with(this@PokemonDetailsActivity).load(pokemon.imageUrl).into(ivPokemonImage)
            val idText = "Nº ${pokemon.formattedId}"
            tvPokemonId.text = idText
            tvPokemonName.text = pokemon.formattedName
            for (i in pokemon.types.indices) {
                val pkmTypeName = pokemon.types[i].name
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
            if (pokemon.types.size <= 1) {
                tvType2.visibility = View.GONE
            }

            tvHeight.text = pokemon.formattedHeight
            tvWeight.text = pokemon.formattedWeight
            tvHp.text = pokemon.formattedHpStat
            tvSpeed.text = pokemon.formattedSpeedStat
            tvAttack.text = pokemon.formattedAtkStat
            tvDefense.text = pokemon.formattedDefStat
            tvSpAttack.text = pokemon.formattedSpAtkStat()
            tvSpDefense.text = pokemon.formattedSpDefStat()
        }

        binding.btBack.setOnClickListener {
            onBackPressed()
        }
    }
}