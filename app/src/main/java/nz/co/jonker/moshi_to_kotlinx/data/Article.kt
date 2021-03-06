package nz.co.jonker.moshi_to_kotlinx.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("story")
data class Article(
    val headline: String,
    val teaserImage: Image
) : ListItem