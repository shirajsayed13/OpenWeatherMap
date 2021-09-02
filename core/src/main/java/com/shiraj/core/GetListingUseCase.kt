package com.shiraj.core

import javax.inject.Inject

class GetListingUseCase @Inject constructor(
    private val listingWebService: ListingWebService
) {

    suspend operator fun invoke(): List<ListingItem> {
        return listingWebService.getListItems()
    }
}