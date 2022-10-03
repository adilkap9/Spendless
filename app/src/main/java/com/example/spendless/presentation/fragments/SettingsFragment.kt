package com.example.spendless.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spendless.R
import com.example.spendless.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var bindingSettings : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingSettings = FragmentSettingsBinding.inflate(inflater)
        return bindingSettings.root
    }

}