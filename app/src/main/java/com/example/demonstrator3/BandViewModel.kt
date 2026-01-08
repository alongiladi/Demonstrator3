package com.example.demonstrator3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.demonstrator3.data.BandItem
import com.example.demonstrator3.data.BandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BandViewModel(private val repository: BandRepository) : ViewModel() {

    val allItems: Flow<List<BandItem>> = repository.allItems

    private val _generatedBandName = MutableStateFlow("")
    val generatedBandName: StateFlow<String> = _generatedBandName

    fun addItem(name: String, genre: String, notes: String) {
        val newItem = BandItem(name = name, genre = genre, notes = notes)
        viewModelScope.launch {
            repository.insert(newItem)
        }
    }

    fun deleteItem(item: BandItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun updateItem(item: BandItem) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun generateBandName(genre: String, notes: String) {
        viewModelScope.launch {
            val prompt = "Generate a creative band name for a $genre band. Notes: $notes"
            val result = repository.generateBandName(prompt)
            _generatedBandName.value = result
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = (extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BandForgeApplication)
                return BandViewModel(application.bandRepository) as T
            }
        }
    }
}