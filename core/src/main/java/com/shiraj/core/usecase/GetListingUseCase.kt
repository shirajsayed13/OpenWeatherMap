package com.shiraj.core.usecase

import com.shiraj.core.webservice.ListingWebService
import com.shiraj.core.model.ListingItem
import javax.inject.Inject

class GetListingUseCase @Inject constructor(
    private val listingWebService: ListingWebService
) {

    suspend operator fun invoke(): List<ListingItem> {
        return listingWebService.getListItems()
    }
}