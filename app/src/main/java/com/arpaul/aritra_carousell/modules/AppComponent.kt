package com.arpaul.aritra_carousell.modules

import com.arpaul.aritra_carousell.viewmodel.BannerVM
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    /**
     * Injects required dependencies into the specified BannerVM.
     * @param beerVM BannerVM in which to inject the dependencies
     */
    abstract fun inject(beerVM: BannerVM)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun networkModule(networkModule: RetrofitModule): Builder
    }


}