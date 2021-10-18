package com.indocyber.pokeapp.di

import com.indocyber.pokeapp.domain.usecases.GetDetailsUseCase
import com.indocyber.pokeapp.domain.usecases.GetPokemonUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokemonUseCase(get()) }
    factory { GetDetailsUseCase(get()) }
}