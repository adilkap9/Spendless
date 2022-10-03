package com.example.spendless.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.spendless.R
import com.example.spendless.databinding.ActivityMainBinding
import com.example.spendless.presentation.fragments.HistoryFragment
import com.example.spendless.presentation.fragments.MainFragment
import com.example.spendless.presentation.fragments.SettingsFragment
import com.example.spendless.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        openFragment(MainFragment(), R.id.fragmentMain)

        bindingMain.bottomNavigation.selectedItemId = R.id.home
        bindingMain.bottomNavigation.setOnItemSelectedListener() {
            when(it.itemId){
                R.id.history ->openFragment(HistoryFragment(), R.id.fragmentMain)
                R.id.home ->openFragment(MainFragment(), R.id.fragmentMain)
                R.id.settings ->openFragment(SettingsFragment(), R.id.fragmentMain)
            }
            true
        }
    }

    private fun openFragment(fragment: Fragment, frameLayoutId: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(frameLayoutId, fragment)
            .commit()
    }

}