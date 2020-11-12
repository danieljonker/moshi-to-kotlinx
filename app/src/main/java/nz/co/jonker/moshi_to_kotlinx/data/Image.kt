package nz.co.jonker.moshi_to_kotlinx.data

import kotlinx.serialization.Serializable
import nz.co.jonker.moshi_to_kotlinx.json.ImageSerializer

@Serializable(with = ImageSerializer::class)
data class Image(
    val imageUrl: String,
    val caption: String,
    val altText: String,
)
