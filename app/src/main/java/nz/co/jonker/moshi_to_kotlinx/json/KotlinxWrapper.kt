package nz.co.jonker.moshi_to_kotlinx.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import nz.co.jonker.moshi_to_kotlinx.data.*

val module = SerializersModule {
    polymorphic(ListItem::class, Article::class, Article.serializer())
    polymorphic(ListItem::class, Video::class, Video.serializer())
    polymorphicDefault(ListItem::class) { Invalid.serializer() }
}

val format = Json {
    serializersModule = module
    ignoreUnknownKeys = true
}

class KotlinxWrapper : NewsListJsonWrapper {

    override fun fromJson(json: String): NewsList? {
        return format.decodeFromString(NewsList.serializer(), json)
    }
}