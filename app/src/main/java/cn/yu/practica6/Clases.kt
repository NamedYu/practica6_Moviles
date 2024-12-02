package cn.yu.practica6

import kotlinx.serialization.Serializable


data class Pelicula (
    val id: Int,
    val url: String?,
    val name: String?,
    val type: String?,
    val language: String?,
    val genres: List<String>?,
    val status: String?,
    val runtime: Int?,
    val averageRuntime: Int?,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Double?,
    val weight: Int?,
    val network: Network?,
    val webChannel: String?,
    val dvdCountry: String?,
    val externals: Externals?,
    val image: Image?,
    val summary: String?,
    val updated: Long?,
    val _links: Links?
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Network(
    val id: Int,
    val name: String,
    val country: Country?,
    val officialSite: String?
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class Externals(
    val tvrage: Int?,
    val thetvdb: Int?,
    val imdb: String?
)

data class Image(
    val medium: String,
    val original: String
)

data class Links(
    val self: Link,
    val previousepisode: Link?
)

data class Link(
    val href: String
)