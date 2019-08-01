package com.arpaul.aritra_carousell.viewmodel

import androidx.lifecycle.MutableLiveData
import com.arpaul.aritra_carousell.common.Resource
import com.arpaul.aritra_carousell.modules.data.AdBanner
import com.arpaul.aritra_carousell.webservices.APICall
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BannerVM : BaseVM() {

    @Inject
    lateinit var apiCall: APICall

    private lateinit var subscription: Disposable

    private var bannerLiveData: MutableLiveData<Resource<List<AdBanner>>>? = null

    fun init() {
        if (bannerLiveData != null)
            return

        loadBanners()
    }

    fun getBannersMutable(): MutableLiveData<Resource<List<AdBanner>>>? {
        bannerLiveData = MutableLiveData()
        return bannerLiveData
    }

    fun loadBanners() {
        subscription = apiCall.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onAPILoading() }
            .doOnTerminate { onAPITerinate() }
            .subscribe(
                { result -> onAPISuccess(result) },
                { error -> onAPIError(error) }
            )
    }

    private fun onAPILoading(){
        Resource.loading(null)
    }

    private fun onAPITerinate(){
    }

    private fun onAPISuccess(bannerList:List<AdBanner>){
        bannerList?.let {
            bannerLiveData!!.value = Resource.success(bannerList)
        }

    }

    private fun onAPIError(error: Throwable){
        bannerLiveData!!.value = Resource.error(error.message, null)
    }
}
