package com.indocyber.pokeapp.domain.usecases

import com.indocyber.pokeapp.domain.repos.PokeRepository

class GetPokemonUseCase(private val repository: PokeRepository) {
    operator fun invoke() = repository.retrievePokemonFlow()
}