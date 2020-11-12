package nz.co.jonker.moshi_to_kotlinx.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("video")
data class Video(
    val headline: String,
    val teaserImage: Image,
    val videoUrl: String
) : ListItem
