package com.example.paging3.prentation.screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.paging3.common.Constant
import com.example.paging3.common.Constant.BASE_URL
import com.example.paging3.common.Constant.BASE_URL_IMG
import com.example.paging3.data.repository.UnsplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repositoryImpl: UnsplashRepositoryImpl
) : ViewModel() {

    private val _action: MutableSharedFlow<HomeAction> = MutableSharedFlow()
    val action = _action.asSharedFlow()

    val getAllImages = repositoryImpl.getAllImages()

    fun downloadImg(url: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repositoryImpl.downloadImage(url.split(BASE_URL_IMG).last())
                response.body()?.let { body ->
                    val file = File(context.cacheDir, url.split(BASE_URL_IMG).last() +".jpg")
                    val outputStream = FileOutputStream(file)
                    outputStream.use { stream ->
                        try {
                            stream.write(body.bytes())
                        } catch (e: IOException) {
                            Log.d("LOGDIMA",""+e.message)
                        }
                    }

                }
                setAction(HomeAction.ShowToast(response.isSuccessful))

            }

        }
    }

    fun setAction(action: HomeAction) {
        viewModelScope.launch { _action.emit(action) }
    }
}