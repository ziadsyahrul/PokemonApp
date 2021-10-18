package com.indocyber.pokeapp.domain.mapper

import com.indocyber.pokeapp.data.network.model.PokemonDetailsResponse
import com.indocyber.pokeapp.domain.models.Pokemon

interface NetworkMapper {
    fun mapPokemonDetailsResponseToDomain(responseList: List<PokemonDetailsResponse>): List<Pokemon>
}