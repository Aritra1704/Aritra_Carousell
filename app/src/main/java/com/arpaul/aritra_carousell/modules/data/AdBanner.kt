package com.arpaul.aritra_carousell.modules.data

data class AdBanner(
    val id: String,
    val title: String,
    val description: String,
    val banner_url: String,
    val time_created: Long,
    val rank: Int
) {}