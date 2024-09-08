package com.example.cats.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.usecase.GetCatsUseCase
import com.example.cats.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    private val _allCats = MutableLiveData<Resource<List<CatResponse>>>()

    val allCats: LiveData<Resource<List<CatResponse>>>
        get() = _allCats

    fun getAllCats() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatsUseCase.invoke().catch {
                Log.d("YUSUFAYDIN", it.toString())
            }.collect {
                _allCats.postValue(it)
            }
        }
    }

}