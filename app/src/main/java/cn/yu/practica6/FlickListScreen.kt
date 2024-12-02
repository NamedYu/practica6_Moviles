package cn.yu.practica6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlickListScreen(
    modifier: Modifier = Modifier,
    peliculalista: List<Pelicula>,
    onPeliClick: (Pelicula) -> Unit
) {
    Scaffold (
        modifier = Modifier,
    ) {
        innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            peliculalista.chunked(2).forEach { rowItems ->
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

@Composable
fun PeliculaItem(
    pelicula: Pelicula,
    onPeliClick: (Pelicula) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {

    }
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onPeliClick(pelicula) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .fillMaxWidth()
            ) {
                val imageUrl = pelicula.image?.medium ?: "your_default_image_url" // 设置默认图像 URL
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = pelicula.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // 左上角显示 Rating
                pelicula.rating?.let {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(4.dp)
                            .background(
                                Color(0xFFBBE1FA),
                                shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "$it",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pelicula.name ?: "Unknown",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = pelicula.genres?.joinToString(separator = ",") ?:"Hola",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}