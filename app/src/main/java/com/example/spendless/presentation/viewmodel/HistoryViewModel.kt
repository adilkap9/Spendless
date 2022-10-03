package com.example.spendless.presentation.viewmodel

import androidx.lifecycle.*
import com.example.spendless.domain.db.ExpenseModel
import com.example.spendless.domain.db.ExpenseRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: ExpenseRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allExpenses: LiveData<List<ExpenseModel>> = repository.allExpenses.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(expense: ExpenseModel) = viewModelScope.launch {
        repository.insert(expense)
    }
}

class HistoryViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}