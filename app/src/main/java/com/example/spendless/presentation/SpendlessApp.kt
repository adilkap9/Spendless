package com.example.spendless.presentation

import android.app.Application
import com.example.spendless.domain.db.ExpenseRepository
import com.example.spendless.domain.db.ExpenseRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SpendlessApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ExpenseRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExpenseRepository(database.expenseDao()) }

    override fun onCreate() {
        super.onCreate()
        //
    }

}