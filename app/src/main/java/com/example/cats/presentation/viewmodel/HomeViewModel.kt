package com.example.cats.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.usecase.GetCatsUseCase
import com.example.cats.util.Resource
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
    private val _allCats = MutableStateFlow<Resource<List<CatResponse>>>(Resource.loading(null))
    val allCats: StateFlow<Resource<List<CatResponse>>> = _allCats


    fun getAllCats() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatsUseCase.invoke().catch {
                Log.d("YUSUFAYDIN", it.toString())
            }.collect { cats ->
                _allCats.value = cats
            }
        }
    }

}