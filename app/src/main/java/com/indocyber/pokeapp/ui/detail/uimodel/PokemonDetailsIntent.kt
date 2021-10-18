package com.indocyber.pokeapp.ui.detail.uimodel

sealed class PokemonDetailsIntent {
    object DetailsBackClick: PokemonDetailsIntent()
    object DetailsButtonBackClick: PokemonDetailsIntent()
}