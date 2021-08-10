package com.bironader.currencyx.datasource.remote.responses

import com.google.gson.annotations.SerializedName

data class ListExchangesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("terms") val terms: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("source") val source: String,
    @SerializedName("quotes") val quotes: Map<String, Double>
)
