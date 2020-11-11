package nz.co.jonker.moshi_to_kotlinx.data

data class Image(
    val imageUrl: String,
    val caption: String,
    val altText: String,
    val source: String
)
