package com.shiraj.core


interface ListingWebService {
    suspend fun getListItems(): List<ListingItem>
}