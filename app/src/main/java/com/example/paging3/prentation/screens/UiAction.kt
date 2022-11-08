package com.example.paging3.prentation.screens

interface UiAction {
}

sealed class HomeAction : UiAction {
    data class ShowToast(val isSuccess : Boolean) : HomeAction()
}