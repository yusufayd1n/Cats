package com.example.cats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.cats.domain.usecase.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    val getAllCats = getCatsUseCase.invoke().cachedIn(viewModelScope)
}