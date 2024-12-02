package cn.yu.practica6.navigation
import cn.yu.practica6.Pelicula
import kotlinx.serialization.Serializable


@Serializable
object FlickLista

@Serializable
object FlickSearchList

@Serializable
object FavoriteList

@Serializable
data class FlickDetailData(val peliculaId: Int)
