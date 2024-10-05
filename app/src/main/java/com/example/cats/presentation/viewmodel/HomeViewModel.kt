package com.example.cats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cats.data.remote.api.ApiResult
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.usecase.GetCatsUseCase
import com.example.cats.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    private val _allCats = MutableStateFlow<UIState<List<CatResponse>>>(UIState.Loading)
    val allCats: StateFlow<UIState<List<CatResponse>>> = _allCats


    fun getAllCats() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatsUseCase.invoke().catch {}.collect { response ->
                when (response) {
                    is ApiResult.Error -> {
                        _allCats.value = UIState.Error(response.exception.message ?: "Unkown Error")
                    }

                    ApiResult.Loading -> {
                        _allCats.value = UIState.Loading
                    }

                    is ApiResult.Success -> {
                        _allCats.value = UIState.Success(response.data)
                    }
                }
            }
        }
    }

}