package com.example.spendless.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Query("SELECT * FROM expense_table ORDER BY date ASC")
    fun getSortedExpenses(): Flow<List<ExpenseModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createExpense(expense: ExpenseModel)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()

}