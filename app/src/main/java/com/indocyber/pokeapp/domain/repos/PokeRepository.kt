package com.indocyber.pokeapp.domain.repos

import androidx.paging.PagingData
import com.indocyber.pokeapp.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokeRepository {

    fun retrievePokemonFlow(): Flow<PagingData<Pokemon>>
    suspend fun retrieveSinglePokemon(pokemonId: Int): Flow<Pokemon?>
}