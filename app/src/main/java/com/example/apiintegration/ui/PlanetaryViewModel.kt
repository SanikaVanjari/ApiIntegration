package com.example.apiintegration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.data.PlanetaryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetaryViewModel @Inject constructor(var repository: PlanetaryRepository) : ViewModel() {
    private val _planetaryData = MutableStateFlow<Resource<PlanetaryData>>(Resource.Empty())
    val planetaryData: StateFlow<Resource<PlanetaryData>>
        get() = _planetaryData

    fun getPlanetaryData() = viewModelScope.launch {
        _planetaryData.emit(Resource.Loading())
        val response = repository.getNasaData()
        if (response.isSuccessful) {
            response.body()?.let {
                _planetaryData.emit(Resource.Success(it))
            }
        } else{
            _planetaryData.emit(Resource.Error(response.errorBody().toString()))
        }
    }
}

sealed class Resource<T>(
    val data: PlanetaryData? = null,
    val message: String? = null
) {
    class Success<T>(data: PlanetaryData) : Resource<T>(data)
    class Error<T>(message: String, data: PlanetaryData? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class Empty<T> : Resource<T>()
}