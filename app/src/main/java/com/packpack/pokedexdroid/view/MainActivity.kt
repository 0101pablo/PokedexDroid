package com.packpack.pokedexdroid.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.packpack.pokedexdroid.R
import com.packpack.pokedexdroid.databinding.ActivityMainBinding
import com.packpack.pokedexdroid.domain.Pokemon
import com.packpack.pokedexdroid.viewmodel.PokemonViewModel
import com.packpack.pokedexdroid.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        recyclerView = binding.rvPokemons
        viewModel.pokemons.observe(this) {
            loadRecyclerView(it)
        }
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}