package com.example.spendless.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.spendless.presentation.viewmodel.MainViewModel
import com.example.spendless.databinding.FragmentMainBinding
import com.example.spendless.domain.db.ExpenseModel
import com.example.spendless.presentation.SpendlessApp
import com.example.spendless.presentation.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((activity?.application as SpendlessApp).repository)
    }
    private lateinit var bindingMainFrag : FragmentMainBinding
    private var expense: Int = 0
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingMainFrag = FragmentMainBinding.inflate(inflater)
        return bindingMainFrag.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getAllExpensesSum()
        mainViewModel.totalExpense.observe(viewLifecycleOwner) {
            bindingMainFrag.tvExpValue.text = mainViewModel.totalExpense.value.toString()
        }

        bindingMainFrag.btAdd.setOnClickListener {
            bindingMainFrag.editText.visibility = View.VISIBLE
            bindingMainFrag.btApply.visibility = View.VISIBLE
            bindingMainFrag.btAdd.visibility = View.GONE
        }

        bindingMainFrag.btApply.setOnClickListener {

            mainViewModel.loggingExpense()

            var editTextValue = bindingMainFrag.editText.text.toString()


            if (editTextValue.isNotEmpty()) {
                expense = bindingMainFrag.editText.text.toString().toInt()
                if ( editTextValue.toInt() in 0..Int.MAX_VALUE) {
                    if (mainViewModel.totalExpense.value == null) {
                        mainViewModel.totalExpense.value = expense
                        mainViewModel.insert(ExpenseModel(editTextValue.toInt(), currentDate))
                    } else {
                        mainViewModel.totalExpense.value = mainViewModel.totalExpense.value?.plus(expense)
                        mainViewModel.insert(ExpenseModel(editTextValue.toInt(), currentDate))
                    }

                    bindingMainFrag.btApply.visibility = View.GONE
                    bindingMainFrag.editText.text.clear()
                    bindingMainFrag.editText.visibility = View.GONE
                    bindingMainFrag.btAdd.visibility = View.VISIBLE
                }
            } else {
                Log.d("DATAMODEL", "Empty")
            }
        }


    }


}