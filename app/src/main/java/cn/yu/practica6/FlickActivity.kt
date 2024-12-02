//package cn.yu.practica6
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import cn.yu.practica3.FlickDetailPage
//import java.security.Provider
//
//class FlickActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val peliculaID = intent.getIntExtra("peliculaID",0)
//        val pelicula = DataProvider(this).peliculas.find { it.id == peliculaID }
//        setContent {
//            FlickDetailPage(
//                pelicula = pelicula!!,
//                onBack = {finish()}
//            )
//        }
//    }
//}