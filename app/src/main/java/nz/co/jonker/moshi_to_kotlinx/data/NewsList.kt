package nz.co.jonker.moshi_to_kotlinx.data

import kotlinx.serialization.Serializable
import nz.co.jonker.moshi_to_kotlinx.json.NewsFeedSerializer

@Serializable(with = NewsFeedSerializer::class)
data class NewsList(val name: String, val data: List<ListItem>)