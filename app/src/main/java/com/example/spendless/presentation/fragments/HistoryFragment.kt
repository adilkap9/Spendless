package com.example.spendless.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendless.R
import com.example.spendless.databinding.FragmentHistoryBinding
import com.example.spendless.presentation.SpendlessApp
import com.example.spendless.presentation.adapter.ExpenseListAdapter
import com.example.spendless.presentation.viewmodel.HistoryViewModel
import com.example.spendless.presentation.viewmodel.HistoryViewModelFactory


class HistoryFragment : Fragment() {

    private val hisoryViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((activity?.application as SpendlessApp).repository)
    }

    private lateinit var bindingHistory : FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHistory = FragmentHistoryBinding.inflate(inflater)
        return bindingHistory.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = bindingHistory.recyclerview
        val adapter = ExpenseListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        hisoryViewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            expenses?.let { adapter.submitList(it) }
        }
    }
}