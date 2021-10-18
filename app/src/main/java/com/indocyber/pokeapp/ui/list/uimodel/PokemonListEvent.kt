package com.indocyber.pokeapp.ui.list.uimodel

import com.indocyber.pokeapp.domain.models.Pokemon
import io.uniflow.core.flow.data.UIEvent


sealed class PokemonListEvent: UIEvent() {
    data class GoToDetail(val pokemon: Pokemon) : PokemonListEvent()
}