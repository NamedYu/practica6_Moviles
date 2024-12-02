package cn.yu.practica6

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
class FlickListViewModel:ViewModel() {
    val flickList = mutableStateOf( DataProvider.peliculas)
}