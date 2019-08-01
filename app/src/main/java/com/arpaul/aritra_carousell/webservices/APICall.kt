package com.arpaul.aritra_carousell.webservices

import com.arpaul.aritra_carousell.modules.data.AdBanner
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.ArrayList

interface APICall {
    @GET("carousell_news.json")
    abstract fun getBanners(): Observable<ArrayList<AdBanner>>
}