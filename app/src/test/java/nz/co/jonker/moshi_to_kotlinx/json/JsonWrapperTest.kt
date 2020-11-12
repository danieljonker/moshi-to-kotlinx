package nz.co.jonker.moshi_to_kotlinx.json

import junit.framework.TestCase
import nz.co.jonker.moshi_to_kotlinx.data.*
import nz.co.jonker.moshi_to_kotlinx.moshi

class JsonWrapperTest : TestCase() {
    private val moshiWrapper: NewsListJsonWrapper = MoshiWrapper(moshi)
    private val kotlinxWrapper: NewsListJsonWrapper = KotlinxWrapper()

    private val expectedArticle = Article(
        "Article Headline",
        "Article Teaser Headline ",
        Image(
            "article/url",
            "Article Caption",
            "Article Alt Text",
            "PA"
        )
    )
    private val expectedVideo = Video(
        "Video Headline",
        "Video Teaser Headline",
        Image(
            "/video/image/url",
            "Video image alt text",
            "Video image alt text",
            "PA"
        )
    )

    private val expectedData = NewsList("UK", listOf(expectedArticle, expectedVideo, Invalid))

    // Test that the JsonWrapper is deserializing our sample data as we intend
    fun `test moshi json deserialization`() {
        val deserializedJson = moshiWrapper.fromJson(sampleJson)

        assertEquals(expectedData, deserializedJson)
    }

    fun `test kotlinx json deserialization`() {
        val deserializedJson = kotlinxWrapper.fromJson(sampleJson)

        assertEquals(expectedData, deserializedJson)
    }
}

private val sampleJson = """
{
  "type": "news_list",
  "name": "UK",
  "_embedded": {
    "contents": [
      {
        "id": 1234,
        "teaserHeadline": "Article Teaser Headline ",
        "headline": "Article Headline",
        "type": "story",
        "creationDate": "2020-11-11T08:20:00Z",
        "lastUpdate": "2020-11-11T12:12:00Z",
        "teaserImage": {
          "_links": {
            "url": {
              "href": "article/url",
              "templated": true,
              "type": "image/jpeg"
            }
          },
          "id": 2,
          "altText": "Article Alt Text",
          "caption": "Article Caption",
          "source": "PA"
        }
      },
      {
        "id": 5678,
        "videoUrl": "https://youtu.be/9Auq9mYxFEE",
        "teaserHeadline": "Video Teaser Headline",
        "headline": "Video Headline",
        "type": "video",
        "creationDate": "2020-11-11T11:44:38Z",
        "lastUpdate": "2020-11-11T11:58:12Z",
        "teaserImage": {
          "_links": {
            "url": {
              "href": "/video/image/url",
              "templated": true,
              "type": "image/jpeg"
            }
          },
          "id": 9876,
          "altText": "Video image alt text",
          "caption": null,
          "source": "PA"
        },
        "durationInSeconds": 91,
        "videoType": "vod",
        "aspectRatio": "1:1"
      },
      {
      "id": 5678,
      "headline": "Some other headline",
      "type": "something_else"
      }
    ]
  }
}
"""