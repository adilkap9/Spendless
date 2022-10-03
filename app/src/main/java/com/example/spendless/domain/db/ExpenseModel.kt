package com.example.spendless.domain.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
class ExpenseModel (
    val amount: Int,
    @ColumnInfo(name = "date") val date: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
        )