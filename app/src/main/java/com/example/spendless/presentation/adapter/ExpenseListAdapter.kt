package com.example.spendless.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spendless.R
import com.example.spendless.domain.db.ExpenseModel

class ExpenseListAdapter : ListAdapter<ExpenseModel, ExpenseListAdapter.ExpenseViewHolder>(ExpensesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.amount.toString())
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val expenseItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            expenseItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ExpenseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ExpenseViewHolder(view)
            }
        }
    }

    class ExpensesComparator : DiffUtil.ItemCallback<ExpenseModel>() {
        override fun areItemsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
            return oldItem.amount == newItem.amount
        }
    }
}