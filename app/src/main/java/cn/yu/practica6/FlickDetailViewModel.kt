package cn.yu.practica6

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import cn.yu.practica6.navigation.FlickDetailData
import cn.yu.practica6.navigation.FlickLista

class FlickDetailViewModel(
    savedStateHandle: SavedStateHandle
):ViewModel() {
    val peliculaId: Int = savedStateHandle.toRoute<FlickDetailData>().peliculaId
    val pelicula = DataProvider.peliculas.get(peliculaId-1)
}