package com.indocyber.pokeapp.data.network

import com.indocyber.pokeapp.data.network.model.PokemonDetailsResponse
import com.indocyber.pokeapp.data.network.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApi {

    @GET
    suspend fun getPokemonListByUrl(@Url url: String): PokemonListResponse

    @GET
    suspend fun getPokemonDetailsByUrl(@Url url: String): PokemonDetailsResponse

}