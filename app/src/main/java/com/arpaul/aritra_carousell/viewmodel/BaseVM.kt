package com.arpaul.aritra_carousell.viewmodel

import androidx.lifecycle.ViewModel
import com.arpaul.aritra_carousell.modules.AppComponent
import com.arpaul.aritra_carousell.modules.DaggerAppComponent
import com.arpaul.aritra_carousell.modules.RetrofitModule

abstract class BaseVM : ViewModel() {
    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .networkModule(RetrofitModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is BannerVM -> injector.inject(this)
        }
    }
}