package com.indocyber.pokeapp.ui.list

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.indocyber.pokeapp.domain.models.Pokemon
import com.indocyber.pokeapp.domain.usecases.GetPokemonUseCase
import com.indocyber.pokeapp.ui.list.uimodel.PokemonListEvent
import com.indocyber.pokeapp.ui.list.uimodel.PokemonListIntent
import com.indocyber.pokeapp.ui.list.uimodel.PokemonListStates
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.actionOn
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
): AndroidDataFlow() {

    val intentChannel = Channel<PokemonListIntent>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when(intent) {
                    is PokemonListIntent.ListStateChange -> {
                        manageListStateChangeIntent(intent)
                    }
                    is PokemonListIntent.PokemonItemClick -> {
                        sendOpenDetailEvent(intent.pokemon)
                    }
                }
            }
        }
    }

    private fun manageListStateChangeIntent(intent: PokemonListIntent.ListStateChange) {
        when {
            intent.state.refresh is LoadState.Loading && intent.itemsLoaded == 0 -> {
                sendLoadingEvent()
            }
            intent.state.refresh is LoadState.NotLoading && intent.state.append is LoadState.NotLoading && intent.state.prepend is LoadState.NotLoading && intent.itemsLoaded > 0 -> {
                sendSuccessEvent()
            }
            intent.state.refresh is LoadState.Error && intent.itemsLoaded == 0  -> {
                setErrorState()
                sendSuccessEvent()
            }
        }
    }

    private fun sendSuccessEvent() = action {
        sendEvent(UIEvent.Success)
    }

    fun initFlow() = actionOn<UIState.Empty>  {
        getPokemonsFlow()
    }

    private fun sendLoadingEvent() = action {
        sendEvent(UIEvent.Loading)
    }

    private fun getPokemonsFlow() {
        viewModelScope.launch {
            getPokemonUseCase()
                .catch {
                    it.printStackTrace()
                    setErrorState()
                }
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    setListState(pagingData)
                }
        }
    }

    private fun setListState(pagingData: PagingData<Pokemon>) = action {
        setState { PokemonListStates.ListPresent(pagingData) }
    }

    private fun setErrorState() = action {
        setState { PokemonListStates.ListError }
    }

    private fun sendOpenDetailEvent(pokemon: Pokemon) = action {
        sendEvent(PokemonListEvent.GoToDetail(pokemon))
    }
}