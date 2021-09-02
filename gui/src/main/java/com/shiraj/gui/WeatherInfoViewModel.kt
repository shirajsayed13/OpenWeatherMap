package com.shiraj.gui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shiraj.base.viewmodel.BaseViewModel
import com.shiraj.core.GetListingUseCase
import com.shiraj.core.ListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    private val getListingUseCase: GetListingUseCase
) : BaseViewModel() {

    private val _feedItems: MutableLiveData<List<ListingItem>> by lazy { MutableLiveData() }
    internal val feedItems: LiveData<List<ListingItem>> = _feedItems

    internal fun loadListing() {
        Timber.d("loadListing: ")
        launchUseCase {
            _feedItems.postValue(getListingUseCase())
        }
    }
}