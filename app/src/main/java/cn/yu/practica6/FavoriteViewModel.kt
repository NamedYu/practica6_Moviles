package cn.yu.practica6

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FavoriteViewModel:ViewModel() {
    val flickList = mutableStateOf( DataProvider.peliculas)
}