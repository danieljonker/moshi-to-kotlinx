package nz.co.jonker.moshi_to_kotlinx.json

import junit.framework.TestCase
import nz.co.jonker.moshi_to_kotlinx.data.*
import nz.co.jonker.moshi_to_kotlinx.moshi

class JsonWrapperTest : TestCase() {
    private val moshiWrapper: NewsListJsonWrapper = MoshiWrapper(moshi)
    private val kotlinxWrapper: NewsListJsonWrapper = KotlinxWrapper()

    private val expectedArticle = Article(
        "Article Headline",
        Image(
            "article/image/url",
            "Image 1 caption",
            "Image 1 altText"
        )
    )
    private val expectedVideo = Video(
        "Video Headline",
        Image(
            "video/image/url",
            "Image 2 altText",
            "Image 2 altText"
        ),
        "video/url"
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
  "name": "UK",
  "_embedded": {
    "contents": [
      {
        "headline": "Article Headline",
        "type": "story",
        "teaserImage": {
          "_links": {
            "url": {
              "href": "article/image/url"
            }
          },
          "altText": "Image 1 altText",
          "caption": "Image 1 caption"
        }
      },
      {
        "videoUrl": "video/url",
        "headline": "Video Headline",
        "type": "video",
        "teaserImage": {
          "_links": {
            "url": {
              "href": "video/image/url"
            }
          },
          "altText": "Image 2 altText",
          "caption": null
        }
      },
      {
        "key": "value",
        "type": "something_else"
      }
    ]
  }
}
"""