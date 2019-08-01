package com.arpaul.aritra_carousell.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpaul.aritra_carousell.R
import com.arpaul.aritra_carousell.common.Resource
import com.arpaul.aritra_carousell.ui.adapters.BannerAdapters
import com.arpaul.aritra_carousell.viewmodel.BannerVM

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find


class DashboardActivity : AppCompatActivity() {

    private var bannerVM: BannerVM? = null
    lateinit var rvBanner: RecyclerView
    lateinit var tvNoData: TextView
    lateinit var pbLoading: ProgressBar
    lateinit var adapter: BannerAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initialiseControls()

        bannerVM = ViewModelProviders.of(this).get(BannerVM::class.java)
        bannerVM!!.init()
        bannerVM!!.getBannersMutable()?.let {
            it.observe(this, Observer {it ->
                when(it.status) {
                    Resource.LOADING -> pbLoading.visibility = View.VISIBLE
                    Resource.SUCCESS -> {
                        pbLoading.visibility = View.GONE
                        tvNoData.visibility = View.GONE
                        it?.let {
                            it.data?.let {
                                adapter.refresh(it)
                            }

                        }
                    }
                    Resource.ERROR -> {
                        pbLoading.visibility = View.GONE
                        tvNoData.visibility = View.VISIBLE
                        rvBanner.visibility = View.GONE
                    }

                }
            })
        }

        setAdapter()

    }

    fun setAdapter() {
        adapter = BannerAdapters(this)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rvBanner.setLayoutManager(mLayoutManager)
        rvBanner.setItemAnimator(DefaultItemAnimator())
        rvBanner.setAdapter(adapter)
    }

    fun initialiseControls() {
        rvBanner = find(R.id.rvBeer)
        tvNoData = find(R.id.tvNoData)
        pbLoading = find(R.id.pbLoading)
    }
}
