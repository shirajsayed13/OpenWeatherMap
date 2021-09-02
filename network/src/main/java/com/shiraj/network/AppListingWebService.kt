package com.shiraj.network

import com.shiraj.core.ListingItem
import com.shiraj.core.ListingWebService
import javax.inject.Inject

internal class AppListingWebService @Inject constructor(
    private val listingWebService: RetrofitListingWebService
) : ListingWebService {

    override suspend fun getListItems(): List<ListingItem> = networkCall(
        { listingWebService.getListing("Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40") },
        { response -> response.mainList.map { it.toMainList() } }
    )
}