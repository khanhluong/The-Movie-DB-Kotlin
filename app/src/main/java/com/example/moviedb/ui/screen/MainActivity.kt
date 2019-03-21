package com.example.moviedb.ui.screen

import android.os.Bundle
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.ui.base.BaseActivity
import com.example.moviedb.ui.screen.main.MainFragment
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val viewModel: MainActivityViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
                .commit()
        }
//        supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment? ?: return
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        when (currentFragment) {
            is MainFragment -> if (!currentFragment.onBack()) super.onBackPressed()
            else -> {
                if (currentFragment != null && currentFragment.childFragmentManager.backStackEntryCount >= 1) {
                    currentFragment.childFragmentManager.popBackStack()
                } else {
                    super.onBackPressed()
                }
            }
        }
    }
}
