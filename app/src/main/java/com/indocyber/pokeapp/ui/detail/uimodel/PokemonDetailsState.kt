package com.indocyber.pokeapp.ui.detail.uimodel

import com.indocyber.pokeapp.domain.models.Pokemon
import io.uniflow.core.flow.data.UIState

sealed class PokemonDetailsState : UIState() {
    data class DetailsPresent(val pokemon: Pokemon) : PokemonDetailsState()
    object DetailsError : PokemonDetailsState()
}