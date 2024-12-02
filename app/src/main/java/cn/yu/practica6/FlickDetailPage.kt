package cn.yu.practica3

import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.yu.practica6.Country
import cn.yu.practica6.Externals
import cn.yu.practica6.Link
import cn.yu.practica6.Links
import cn.yu.practica6.Network
import cn.yu.practica6.Pelicula
import cn.yu.practica6.Schedule
import cn.yu.practica6.ui.theme.Practica6Theme
import coil3.compose.rememberAsyncImagePainter // 使用 Coil 来加载网络图片
import coil3.request.ImageRequest
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.file.Paths
import java.util.Spliterator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlickDetailPage(
    modifier: Modifier = Modifier,
    pelicula: Pelicula,
    onBack: () -> Unit
){
    Scaffold(
        modifier = modifier,

    ) { innerPadding ->
        FlickDetail(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            pelicula
        )
    }
}

@Composable
fun FlickDetail(
    modifier: Modifier = Modifier,
    pelicula: Pelicula
){
    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
//        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Card(
                modifier = Modifier
                    .padding(8.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp, 200.dp)
                        .fillMaxWidth()
                ) {
                    val imageUrl = pelicula.image?.original ?: "your_default_image_url"
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = pelicula.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop // Cortar imagen
                    )
                    //Rating
                    pelicula.rating?.let {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(4.dp)
                                .background(
                                    Color(0xFFBBE1FA),
                                    shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp) // 内部文字的边距
                        ) {
                            Text(
                                text = "$it",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                Text(
                    text = pelicula.name ?: "Unknown",
                    style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontSize = 12.sp))
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Genres: ")
                        pop()
                        append(pelicula.genres?.joinToString(separator = ","))
                    },
//                    style = MaterialTheme.typography.bodySmall
//                        .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 1.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontSize = 12.sp))
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Premiered: ")
                        pop()
                        append(pelicula.premiered)
                    },
//                    style = MaterialTheme.typography.titleLarge
//                        .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 1.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontSize = 12.sp))
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Country: ")
                        pop()
                        append(pelicula.network?.country?.name+","+pelicula.network?.country?.code)
                    },
//                    style = MaterialTheme.typography.titleLarge
//                        .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 1.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontSize = 12.sp))
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Lenguage: ")
                        pop()
                        append(pelicula.language)
                    },
//                    style = MaterialTheme.typography.titleLarge
//                        .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 1.dp)
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 15.dp))
        Text(
            text = "Summary",
            style = MaterialTheme.typography.bodyLarge
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        val texto = pelicula.summary?.replace("<p>", "")
            ?.replace("</p>", "")
            ?.replace("<b>", "")
            ?.replace("</b>", "")
        Text(
            text = texto?:"NULL",

            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(vertical = 10.dp)
        )

    }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            ShareButton(pelicula) // Agregar share button
            modifier.padding()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            FavoriteButton(pelicula.id)//Agregar favorite button
        }
    }
}

@Composable
fun ShareButton(pelicula: Pelicula){
    val context = LocalContext.current
    val shareText = "Check out this link: ${pelicula.officialSite}"

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    IconButton(
        onClick = {
            context.startActivity(Intent.createChooser(shareIntent, null))
        },
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share",
            tint = Color.White
        )
    }
}

@Composable
fun FavoriteButton(
    peliculaID: Int
) {
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(isFavorite(context, peliculaID)) }
    IconButton(
        onClick = {
            if (isFavorite) {
                removeFavorite(context,peliculaID)
                isFavorite = false
            } else {
                addFavorite(context,peliculaID)
                isFavorite = true
            }
        },
        modifier = Modifier
            .padding(12.dp)
    ) {
        val iconRes = if (isFavorite) {
            Icon(Icons.Default.Favorite,"isFavorite", tint = Color.Red)
        } else {
            Icon(Icons.Default.FavoriteBorder,"noFavorite")
        }
    }
}

fun isFavorite(context: Context, id: Int):Boolean{
    val fileName = "favorites.json"
    val file = File(context.filesDir, fileName)

    if (file.exists()) {
        val jsonString = file.readText()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            if (item.getString("ID") == id.toString()) {
                return true
            }
        }
    }
    return false
}

fun addFavorite(context: Context, id: Int) {
    val fileName = "favorites.json"
    val file = File(context.filesDir, fileName)

    // Leer archivo
    val jsonString = if (file.exists()) file.readText() else "[]"
    val jsonArray = JSONArray(jsonString)

    // revisar que si existe
    for (i in 0 until jsonArray.length()) {
        val item = jsonArray.getJSONObject(i)
        if (item.getString("ID") == id.toString()) {
            return
        }
    }

    // Agregar al favoritos
    val newFavorite = JSONObject().apply {
        put("ID", id.toString())
    }
    jsonArray.put(newFavorite)

    // Reescribe el archivo
    file.writeText(jsonArray.toString(4))
}

fun removeFavorite(context: Context, id: Int) {
    val fileName = "favorites.json"
    val file = File(context.filesDir, fileName)

    if (file.exists()) {
        val jsonString = file.readText()
        val jsonArray = JSONArray(jsonString)

        // filtrar el que va a eliminar
        val updatedArray = JSONArray()
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            if (item.getString("ID") != id.toString()) {
                updatedArray.put(item)
            }
        }

        // Reescribe el archivo
        file.writeText(updatedArray.toString(4))
    }
}