package com.example.spendless.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spendless.presentation.viewmodel.MainViewModel
import com.example.spendless.databinding.FragmentAddExpBinding
import java.text.SimpleDateFormat
import java.util.*

class AddExpFragment : Fragment() {

    lateinit var bindingAddExp: FragmentAddExpBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var expense: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingAddExp = FragmentAddExpBinding.inflate(inflater)
        return bindingAddExp.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingAddExp.btApply.setOnClickListener {


            var editTextValue = bindingAddExp.editText.text.toString()


            if (editTextValue.isNotEmpty()) {
                expense = bindingAddExp.editText.text.toString().toInt()
                if ( editTextValue.toInt() in 0..Int.MAX_VALUE) {
                    if (mainViewModel.totalExpense.value == null) {
                        mainViewModel.totalExpense.value = expense
                    } else {
                        mainViewModel.totalExpense.value = mainViewModel.totalExpense.value?.plus(expense)
                    }

                }
            } else {
                Log.d("DATAMODEL", "Empty")
            }
        }

    }

}