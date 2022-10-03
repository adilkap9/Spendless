package com.example.spendless.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ExpenseModel::class), version = 1, exportSchema = false)
public abstract class ExpenseRoomDatabase : RoomDatabase() {

    abstract fun expenseDao(): DAO


    companion object {
        @Volatile
        private var INSTANCE: ExpenseRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ExpenseRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseRoomDatabase::class.java,
                    "expense_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}