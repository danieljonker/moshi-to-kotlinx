package nz.co.jonker.moshi_to_kotlinx.data

import kotlinx.serialization.Serializable

interface ListItem { }

@Serializable
object Invalid : ListItem

