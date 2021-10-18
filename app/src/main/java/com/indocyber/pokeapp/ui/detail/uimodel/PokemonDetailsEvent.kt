package com.indocyber.pokeapp.ui.detail.uimodel

import io.uniflow.core.flow.data.UIEvent

sealed class PokemonDetailsEvent: UIEvent() {
    object NavigateBack : PokemonDetailsEvent()
}