package nz.co.jonker.moshi_to_kotlinx.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nz.co.jonker.moshi_to_kotlinx.data.ListItem

@Serializable
@SerialName("NewsList")
internal data class NewsFeedJson(val name: String, val _embedded: Embedded) {}

@Serializable
internal data class Embedded(val contents: List<ListItem>)


@Serializable
@SerialName("Image")
internal data class NewsFeedTeaserImage(val _links: NewsFeedImageLinks, val altText: String, val caption: String?)
@Serializable
internal data class NewsFeedImageLinks(val url: NewsFeedImageUrl)
@Serializable
internal data class NewsFeedImageUrl(val href: String)

