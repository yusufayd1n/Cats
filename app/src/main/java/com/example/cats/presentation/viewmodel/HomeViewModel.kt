package com.example.cats.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cats.domain.usecase.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    val getAllCats = getCatsUseCase.invoke()

    init {
        viewModelScope.launch {
            getAllCats.collect {
                Log.d("YUSUFAYDIN", it.data.toString())
            }
        }
    }
}