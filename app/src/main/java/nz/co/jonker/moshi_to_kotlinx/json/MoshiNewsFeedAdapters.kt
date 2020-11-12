package nz.co.jonker.moshi_to_kotlinx.json

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import nz.co.jonker.moshi_to_kotlinx.data.Image
import nz.co.jonker.moshi_to_kotlinx.data.NewsList

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
            newsList.name,
            Embedded(newsList.data)
        )
    }
}

internal class NewsFeedImageAdapter {
    @FromJson
    fun fromJson(json: NewsFeedTeaserImage): Image {
        return Image(
            json._links.url.href,
            json.caption ?: json.altText,
            json.altText
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
            image.caption
        )
    }
}