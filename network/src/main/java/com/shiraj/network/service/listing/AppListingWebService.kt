package com.shiraj.network.service.listing

import com.shiraj.core.model.ListingItem
import com.shiraj.core.webservice.ListingWebService
import com.shiraj.network.networkCall
import com.shiraj.network.response.toMainList
import javax.inject.Inject

internal class AppListingWebService @Inject constructor(
    private val listingWebService: RetrofitListingWebService
) : ListingWebService {

    override suspend fun getListItems(): List<ListingItem> = networkCall(
        { listingWebService.getListing("Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40") },
        { response -> response.mainList.map { it.toMainList() } }
    )
}