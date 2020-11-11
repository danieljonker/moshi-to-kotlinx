package nz.co.jonker.moshi_to_kotlinx.json

import nz.co.jonker.moshi_to_kotlinx.data.NewsList

interface NewsListJsonWrapper {
    fun fromJson(json: String): NewsList?
}