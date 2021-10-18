package com.indocyber.pokeapp.data.network.mapper

import com.indocyber.pokeapp.data.network.model.PokemonDetailsResponse
import com.indocyber.pokeapp.domain.mapper.NetworkMapper
import com.indocyber.pokeapp.domain.models.Pokemon
import com.indocyber.pokeapp.domain.models.PokemonStat
import com.indocyber.pokeapp.domain.models.PokemonType
import com.indocyber.pokeapp.domain.models.PokemonTypeColor
import java.lang.Exception
import java.util.*

class NetworkMapperImpl: NetworkMapper {

    override fun mapPokemonDetailsResponseToDomain(responseList: List<PokemonDetailsResponse>): List<Pokemon> {
        return responseList.map { details ->
            Pokemon(
                id = details.id,
                name = details.name.capitalize(Locale.ROOT),
                iconUrl = details.sprites.frontDefault,
                bigIconUrl = details.sprites.other.officialArtwork.frontDefault,
                weight = details.weight,
                height = details.height,
                ability = mapAbility(details.abilities),
                types = mapTypes(details.types),
                stats = mapStats(details.stats)
            )
        }
    }

    private fun mapAbility(abilities: List<PokemonDetailsResponse.Ability>): String {
        return abilities.joinToString(", ") {
            it.ability.name.capitalize(Locale.ROOT)
        }
    }

    private fun mapStats(stats: List<PokemonDetailsResponse.Stat>): List<PokemonStat> {
        return stats.map {
            PokemonStat(it.stat.name, it.baseStat)
        }
    }

    private fun mapTypes(types: List<PokemonDetailsResponse.Type>): List<PokemonType> {
        return types.map {
            val typeColor = try {
                PokemonTypeColor.valueOf(it.type.name.toUpperCase(Locale.ROOT))
            } catch (ex: Exception) {
                PokemonTypeColor.UNKNOWN
            }
            PokemonType(it.type.name.capitalize(Locale.ROOT), typeColor.colorInt)
        }
    }
}