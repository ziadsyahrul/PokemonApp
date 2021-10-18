package com.indocyber.pokeapp.di

import com.indocyber.pokeapp.BuildConfig
import com.indocyber.pokeapp.data.db.PokemonDatabase
import com.indocyber.pokeapp.data.db.mapper.DatabaseMapperImpl
import com.indocyber.pokeapp.data.network.PokemonApi
import com.indocyber.pokeapp.data.network.mapper.NetworkMapperImpl
import com.indocyber.pokeapp.data.repo.PokeRepositoryImpl
import com.indocyber.pokeapp.domain.mapper.DatabaseMapper
import com.indocyber.pokeapp.domain.mapper.NetworkMapper
import com.indocyber.pokeapp.domain.repos.PokeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"


val appModule = module {

    single {
        val client = OkHttpClient().newBuilder()

        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
        }

        client.build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(PokemonApi::class.java)
    }

    single { PokemonDatabase.create(androidContext()) }

    single<DatabaseMapper> { DatabaseMapperImpl() }

    single<NetworkMapper> { NetworkMapperImpl() }

    single<PokeRepository> {
        PokeRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }
}