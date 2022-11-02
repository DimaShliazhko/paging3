package com.example.paging3.prentation.screens

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.paging3.data.repository.UnsplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repositoryImpl: UnsplashRepositoryImpl
) : ViewModel() {
    val getAllImages = repositoryImpl.getAllImages()
}