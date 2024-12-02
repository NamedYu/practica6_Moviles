package cn.yu.practica6

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {
    val flickList = mutableStateOf( DataProvider.peliculas)
}