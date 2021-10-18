package com.indocyber.pokeapp.domain.usecases

import com.indocyber.pokeapp.domain.repos.PokeRepository

class GetDetailsUseCase(private val repository: PokeRepository) {
    suspend operator fun invoke(pokemonId: Int) = repository.retrieveSinglePokemon(pokemonId)
}