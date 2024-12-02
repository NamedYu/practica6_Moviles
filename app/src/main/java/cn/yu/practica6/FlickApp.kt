package cn.yu.practica6

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.yu.practica6.Externals
import cn.yu.practica6.Link
import cn.yu.practica6.Links
import cn.yu.practica6.ui.theme.Practica6Theme
import cn.yu.practica6.navigation.AppNavigation
import coil3.compose.rememberAsyncImagePainter // 使用 Coil 来加载网络图片
import java.nio.file.Paths

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlickApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home","Search","Favorite")
    val routes = listOf("FlickLista", "FlickSearchList", "FlickFavoriteList") // 与导航的 route 对应
    val currentRoute = navHostController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        navHostController.navigateUp()
                    }){
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                } }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = when (item) {
                                    "Home" -> Icons.Default.Home
                                    "Search" -> Icons.Default.Search
                                    else -> Icons.Default.Favorite
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = currentRoute == routes[index],
                        onClick = {
                            val route = when(item){"Home" -> "FlickLista"
                                "Search" -> "FlickSearchList"
                                "Favorite" -> "FavoriteList"
                                else -> "FlickLista"}
                            navHostController.navigate(route)
                        }
                    )

                }
            }
        }
    ) { innerPadding ->
            AppNavigation(
                modifier = modifier.padding(innerPadding),
                navHostController = navHostController
            )


    }
}


//@Composable
//fun BottomNavigationBar() {
//    BottomNavigation(
//        backgroundColor = MaterialTheme.colorScheme.primary,
//        contentColor = Color.White
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
//            label = { Text("Home") },
//            selected = false, // You can manage selected state based on a state variable
//            onClick = { /* Handle Home click */ }
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
//            label = { Text("Search") },
//            selected = false, // You can manage selected state based on a state variable
//            onClick = { /* Handle Search click */ }
//        )
//    }
//}




@Preview(showBackground = true)
@Composable
fun FlickAppPreview() {
    // 示例数据用于预览
    val examplePeliculas = listOf(
        Pelicula(
            id = 1,
            url = "https://www.tvmaze.com/shows/1/under-the-dome",
            name = "Under the Dome",
            type = "Scripted",
            language = "English",
            genres = listOf("Drama", "Science-Fiction", "Thriller"),
            status = "Ended",
            runtime = 60,
            averageRuntime = 60,
            premiered = "2013-06-24",
            ended = "2015-09-10",
            officialSite = "http://www.cbs.com/shows/under-the-dome/",
            schedule = Schedule(
                time = "22:00",
                days = listOf("Thursday")
            ),
            rating = 6.5,
            weight = 98,
            network = Network(
                id = 2,
                name = "CBS",
                country = Country(
                    name = "United States",
                    code = "US",
                    timezone = "America/New_York"
                ),
                officialSite = "https://www.cbs.com/"
            ),
            webChannel = null,
            dvdCountry = null,
            externals = Externals(
                tvrage = 25988,
                thetvdb = 264492,
                imdb = "tt1553656"
            ),
            image = Image(
                medium = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                original = "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
            ),
            summary = "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
            updated = 1704794065,
            _links = Links(
                self = Link("https://api.tvmaze.com/shows/1"),
                previousepisode = Link("https://api.tvmaze.com/episodes/185054")
            )
        ),
        Pelicula(
            id = 2,
            url = "https://www.tvmaze.com/shows/2/person-of-interest",
            name = "Person of Interest",
            type = "Scripted",
            language = "English",
            genres = listOf("Action", "Crime", "Science-Fiction"),
            status = "Ended",
            runtime = 60,
            averageRuntime = 60,
            premiered = "2011-09-22",
            ended = "2016-06-21",
            officialSite = "http://www.cbs.com/shows/person_of_interest/",
            schedule = Schedule(
                time = "22:00",
                days = listOf("Tuesday")
            ),
            rating = 8.8,
            weight = 98,
            network = Network(
                id = 2,
                name = "CBS",
                country = Country(
                    name = "United States",
                    code = "US",
                    timezone = "America/New_York"
                ),
                officialSite = "https://www.cbs.com/"
            ),
            webChannel = null,
            dvdCountry = null,
            externals = Externals(
                tvrage = 28376,
                thetvdb = 248742,
                imdb = "tt1839578"
            ),
            image = Image(
                medium = "https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg",
                original = "https://static.tvmaze.com/uploads/images/original_untouched/163/407679.jpg"
            ),
            summary = "<p>You are being watched. The government has a secret system, a machine that spies on you every hour of every day. I know because I built it. I designed the Machine to detect acts of terror but it sees everything. Violent crimes involving ordinary people. People like you. Crimes the government considered \"irrelevant\". They wouldn't act so I decided I would. But I needed a partner. Someone with the skills to intervene. Hunted by the authorities, we work in secret. You'll never find us. But victim or perpetrator, if your number is up, we'll find you.</p>",
            updated = 1709303296,
            _links = Links(
                self = Link("https://api.tvmaze.com/shows/2"),
                previousepisode = Link("https://api.tvmaze.com/episodes/659372")
            )
        )
    )
    Practica6Theme {
        FlickApp()
    }
}
