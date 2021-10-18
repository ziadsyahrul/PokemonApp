package com.indocyber.pokeapp.domain.mapper

import com.indocyber.pokeapp.data.db.entity.PokemonEntity
import com.indocyber.pokeapp.domain.models.Pokemon

interface DatabaseMapper {
    fun mapPokemonEntityToDomain(entity: PokemonEntity): Pokemon
    fun mapPokemonDomainToEntities(source: List<Pokemon>): List<PokemonEntity>
}