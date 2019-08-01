package com.arpaul.aritra_carousell.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpaul.aritra_carousell.R
import com.arpaul.aritra_carousell.modules.data.AdBanner
import com.arpaul.utilitieslib.CalendarUtils
import com.arpaul.utilitieslib.CalendarUtils.DATE_TIME_FORMAT_T
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import android.R.attr.y
import android.R.attr.x
import android.app.Activity
import android.view.Display
import android.graphics.Point
import com.arpaul.aritra_carousell.ui.DashboardActivity
import android.util.DisplayMetrics




class BannerAdapters(val context: Context) : RecyclerView.Adapter<BannerAdapters.BannerHolder>() {
    private lateinit var adList: List<AdBanner>

//    init {
//        val displaymetrics = DisplayMetrics()
//        (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
//        //if you need three fix imageview in width
//        val devicewidth = displaymetrics.widthPixels / 3
//
//        //if you need 4-5-6 anything fix imageview in height
//        val deviceheight = displaymetrics.heightPixels / 4
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerAdapters.BannerHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_ad, parent, false)



        return BannerHolder(itemView)
    }

    fun refresh(users: List<AdBanner>) {
        this.adList = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerAdapters.BannerHolder, position: Int) {
        Picasso.get()
            .load(adList.get(position).banner_url)
            .into(holder.ivAdImage);
        holder.tvAdHeader?.let { it.text = adList.get(position).title }
        holder.tvAdBody?.let { it.text = adList.get(position).description }
        holder.tvAdTime?.let {
            it.text =
                CalendarUtils.getDatefromTimeinMilliesPattern(adList.get(position).time_created, DATE_TIME_FORMAT_T)
        }
    }

    override fun getItemCount(): Int {
        return if (::adList.isInitialized) adList.size else 0
    }

    inner class BannerHolder(internal var myView: View) : RecyclerView.ViewHolder(myView) {

        lateinit var ivAdImage: ImageView
        lateinit var tvAdHeader: TextView
        lateinit var tvAdBody: TextView
        lateinit var tvAdTime: TextView

        init {
            ivAdImage = myView.find(R.id.ivAdImage)
            tvAdHeader = myView.find(R.id.tvAdHeader)
            tvAdBody = myView.find(R.id.tvAdBody)
            tvAdTime = myView.find(R.id.tvAdTime)
        }
    }
}