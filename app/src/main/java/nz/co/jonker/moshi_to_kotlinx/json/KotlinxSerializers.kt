package nz.co.jonker.moshi_to_kotlinx.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nz.co.jonker.moshi_to_kotlinx.data.Image
import nz.co.jonker.moshi_to_kotlinx.data.NewsList


object NewsFeedSerializer: KSerializer<NewsList> {
    override val descriptor: SerialDescriptor = NewsFeedJson.serializer().descriptor

    override fun deserialize(decoder: Decoder): NewsList {
        val surrogate = decoder.decodeSerializableValue(NewsFeedJson.serializer())

        return NewsList(
            surrogate.name,
            surrogate._embedded.contents
        )
    }

    override fun serialize(encoder: Encoder, value: NewsList) {
         val surrogate = NewsFeedJson(
            "index",
             value.name,
            Embedded(value.data)
        )

        encoder.encodeSerializableValue(NewsFeedJson.serializer(), surrogate)
    }
}

object ImageSerializer : KSerializer<Image> {
    override val descriptor: SerialDescriptor = NewsFeedTeaserImage.serializer().descriptor

    override fun deserialize(decoder: Decoder): Image {
        val surrogate = decoder.decodeSerializableValue(NewsFeedTeaserImage.serializer())

        return Image(
            surrogate._links.url.href,
            surrogate.caption ?: surrogate.altText,
            surrogate.altText,
            surrogate.source
        )
    }

    override fun serialize(encoder: Encoder, value: Image) {
        val links = NewsFeedImageLinks(
            NewsFeedImageUrl(value.imageUrl)
        )
        val surrogate = NewsFeedTeaserImage(
            links,
            value.altText,
            value.caption,
            value.source
        )
        encoder.encodeSerializableValue(NewsFeedTeaserImage.serializer(), surrogate)
    }
}