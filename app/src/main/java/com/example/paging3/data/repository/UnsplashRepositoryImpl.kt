package com.example.paging3.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3.common.Constant.ITEMS_PER_PAGE
import com.example.paging3.data.local.UnsplashDatabase
import com.example.paging3.data.models.UnsplashImage
import com.example.paging3.data.paging.SearchPagingSource
import com.example.paging3.data.paging.UnsplashRemoteMediator
import com.example.paging3.data.remote.UnsplashApi
import com.example.paging3.data.remote.UnsplashApiImg
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@ExperimentalPagingApi
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashApiImg: UnsplashApiImg,
    private val unsplashDatabase: UnsplashDatabase,
) {


    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

   suspend fun downloadImage(url: String):Response<ResponseBody> {
       return unsplashApiImg.loadImage(url)
    }

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow
    }


}