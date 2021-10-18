package com.indocyber.pokeapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indocyber.pokeapp.domain.models.PokemonStat
import com.indocyber.pokeapp.domain.models.PokemonType


@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val iconUrl: String?,
    val bigIconUrl: String?,
    val weight: Int,
    val height: Int,
    val ability: String,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>
)