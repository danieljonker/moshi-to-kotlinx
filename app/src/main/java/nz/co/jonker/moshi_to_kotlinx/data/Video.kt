package nz.co.jonker.moshi_to_kotlinx.data

data class Video(
    val headline: String,
    val teaserHeadline: String,
    val teaserImage: Image
) : ListItem
