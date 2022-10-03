package com.example.spendless.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.room.Query
import com.example.spendless.domain.db.DAO
import com.example.spendless.domain.db.ExpenseModel
import com.example.spendless.domain.db.ExpenseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

open class MainViewModel(private val repository: ExpenseRepository) : ViewModel() {

    val allExpenses: LiveData<List<ExpenseModel>> = repository.allExpenses.asLiveData()

    val expense : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val totalExpense : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val expenseSum = repository.allExpenses.map {
        var sum = 0
        for (item in it) {
            sum += item.amount
        }
        return@map sum
    }


    fun insert(expense: ExpenseModel) = viewModelScope.launch {
        repository.insert(expense)
    }

    var totalExpenseValue = 0

    fun getAllExpensesSum() = viewModelScope.launch {
        expenseSum.onEach {
                value -> Log.d("WHATSHAPPENING", "value = $value")
            totalExpense.value = value
            Log.d("WHATSHAPPENING", "totalExpenseValue = $totalExpenseValue")
        }.collect()
    }

    fun loggingExpense() {
        Log.d("WHATSHAPPENING", "totalExpenseValue final = $totalExpenseValue")
    }
}

class MainViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}