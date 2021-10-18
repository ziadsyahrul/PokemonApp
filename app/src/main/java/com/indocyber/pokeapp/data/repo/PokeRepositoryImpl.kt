package com.indocyber.pokeapp.data.repo

import androidx.paging.*
import com.indocyber.pokeapp.data.PokeNetworkDbRemoteMediator
import com.indocyber.pokeapp.data.db.PokemonDatabase
import com.indocyber.pokeapp.data.network.PokemonApi
import com.indocyber.pokeapp.domain.mapper.DatabaseMapper
import com.indocyber.pokeapp.domain.mapper.NetworkMapper
import com.indocyber.pokeapp.domain.models.Pokemon
import com.indocyber.pokeapp.domain.repos.PokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PokeRepositoryImpl (
    private val pokemonDb: PokemonDatabase,
    private val pokemonApi: PokemonApi,
    private val dbMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
): PokeRepository {

    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 4
        private const val INIT_URL = "https://pokeapi.co/api/v2/pokemon"
    }

    @ExperimentalPagingApi
    override fun retrievePokemonFlow() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false, prefetchDistance = PREFETCH_DISTANCE),
        remoteMediator = PokeNetworkDbRemoteMediator(pokemonApi, dbMapper, networkMapper, pokemonDb, INIT_URL),
        pagingSourceFactory = { pokemonDb.getPokemonDao().getAllPokemons() }
    ).flow.map { pagingData ->
        pagingData.map {  pokemonEntity ->
            dbMapper.mapPokemonEntityToDomain(pokemonEntity)
        }
    }

    override suspend fun retrieveSinglePokemon(pokemonId: Int): Flow<Pokemon?> {
        with(Dispatchers.IO) {
            val pokemonEntity = pokemonDb.getPokemonDao().getPokemonById(pokemonId)
            return flowOf(
                if(pokemonEntity != null)
                    dbMapper.mapPokemonEntityToDomain(pokemonEntity)
                else
                    null
            )
        }
    }

}