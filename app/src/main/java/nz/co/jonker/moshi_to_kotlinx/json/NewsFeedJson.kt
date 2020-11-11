package nz.co.jonker.moshi_to_kotlinx.json

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import nz.co.jonker.moshi_to_kotlinx.data.Image
import nz.co.jonker.moshi_to_kotlinx.data.ListItem
import nz.co.jonker.moshi_to_kotlinx.data.NewsList

internal data class NewsFeedJson(val type: String, val name: String, val _embedded: Embedded) {}

internal data class Embedded(val contents: List<ListItem>)

/* Convert News Feed to better format */
internal class NewsFeedAdapter {
    @FromJson
    fun fromJson(json: NewsFeedJson): NewsList {
        return NewsList(
            json.name,
            json._embedded.contents
        )
    }

    @ToJson
    fun toJson(newsList: NewsList): NewsFeedJson {
        return NewsFeedJson(
            "index",
            newsList.name,
            Embedded(newsList.data)
        )
    }
}

internal data class NewsFeedImageUrl(val href: String)
internal data class NewsFeedImageLinks(val url: NewsFeedImageUrl)
internal data class NewsFeedTeaserImage(val _links: NewsFeedImageLinks, val altText: String, val caption: String?, val source: String)

internal class NewsFeedImageAdapter {
    @FromJson
    fun fromJson(json: NewsFeedTeaserImage): Image {
        return Image(
            json._links.url.href,
            json.caption ?: json.altText,
            json.altText,
            json.source
        )
    }

    @ToJson
    fun toJson(image: Image): NewsFeedTeaserImage {
        val links = NewsFeedImageLinks(
            NewsFeedImageUrl(image.imageUrl)
        )
        return NewsFeedTeaserImage(
            links,
            image.altText,
            image.caption,
            image.source
        )
    }
}