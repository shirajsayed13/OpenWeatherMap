package com.shiraj.core.webservice

import com.shiraj.core.model.ListingItem


interface ListingWebService {
    suspend fun getListItems(): List<ListingItem>
}