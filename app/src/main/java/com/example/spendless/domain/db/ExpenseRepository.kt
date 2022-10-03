package com.example.spendless.domain.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: DAO) {

    val allExpenses: Flow<List<ExpenseModel>> = expenseDao.getSortedExpenses()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(expense: ExpenseModel) {
        expenseDao.createExpense(expense)
    }



}
