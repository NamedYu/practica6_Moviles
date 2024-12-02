package cn.yu.practica6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.yu.practica3.FlickDetailPage
import cn.yu.practica6.DataProvider
import cn.yu.practica6.FavoriteScreen
import cn.yu.practica6.FavoriteViewModel
import cn.yu.practica6.FlickDetailViewModel
import cn.yu.practica6.FlickListScreen
import cn.yu.practica6.FlickListViewModel
import cn.yu.practica6.SearchScreen
import cn.yu.practica6.SearchViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = "FlickLista"
    ){
        composable("FlickLista") {
            val vs:FlickListViewModel = viewModel()
            FlickListScreen(
                modifier=modifier,
                peliculalista =vs.flickList.value,
                onPeliClick ={pelicula ->
                    navHostController.navigate(FlickDetailData(peliculaId = pelicula.id))
                }
            )
        }

        composable<FlickDetailData> {
            val vs:FlickDetailViewModel = viewModel()
            FlickDetailPage(
                modifier = modifier,
                pelicula = vs.pelicula,
                onBack = {navHostController.navigateUp()}
            )
        }
        composable("FlickSearchList") {
            val vs:SearchViewModel = viewModel()
            SearchScreen(
                modifier=modifier,
                peliculalista =vs.flickList.value,
                onPeliClick ={pelicula ->
                    navHostController.navigate(FlickDetailData(peliculaId = pelicula.id))
                }
            )
        }
        composable("FavoriteList") {
            val vs:FavoriteViewModel = viewModel()
            FavoriteScreen(
                modifier=modifier,
                peliculalista =vs.flickList.value,
                onPeliClick ={pelicula ->
                    navHostController.navigate(FlickDetailData(peliculaId = pelicula.id))
                }
            )
        }

    }
}