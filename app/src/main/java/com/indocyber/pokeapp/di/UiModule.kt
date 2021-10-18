package com.indocyber.pokeapp.di

import com.indocyber.pokeapp.ui.detail.PokemonDetailsViewModel
import com.indocyber.pokeapp.ui.list.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { PokemonDetailsViewModel(get()) }
}