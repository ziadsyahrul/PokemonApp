package com.indocyber.pokeapp.ui.list.uimodel

import androidx.paging.CombinedLoadStates
import com.indocyber.pokeapp.domain.models.Pokemon

sealed class PokemonListIntent {
    class ListStateChange(val itemsLoaded: Int, val state: CombinedLoadStates) : PokemonListIntent()
    class PokemonItemClick(val pokemon: Pokemon) : PokemonListIntent()
}