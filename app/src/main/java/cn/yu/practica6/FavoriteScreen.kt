package cn.yu.practica6

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    peliculalista: List<Pelicula>,
    onPeliClick: (Pelicula) -> Unit
) {
    var favorites = loadFavorites(LocalContext.current,peliculalista)
    Scaffold (
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            favorites.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // crear PeliculaItem para cada objeto
                    rowItems.forEach { pelicula ->
                        PeliculaItem(
                            pelicula = pelicula,
                            onPeliClick = onPeliClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

        }
    }

}

fun loadFavorites(
    context: Context,
    peliculalista: List<Pelicula>
    ):List<Pelicula>{
    val file = File(context.filesDir, "favorites.json")
    if (!file.exists()) return emptyList()

    val jsonString = file.readText()
    val jsonArray = JSONArray(jsonString)
    val favorites = mutableListOf<Pelicula>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        favorites.add(
            peliculalista.get(jsonObject.getInt("ID")-1)
        )
    }
    return favorites
}