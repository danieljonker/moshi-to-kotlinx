package nz.co.jonker.moshi_to_kotlinx.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import nz.co.jonker.moshi_to_kotlinx.data.NewsList

class MoshiWrapper(moshi: Moshi) : NewsListJsonWrapper {

    private val jsonAdapter: JsonAdapter<NewsList> =
        moshi.adapter(NewsList::class.java)

    override fun fromJson(json: String): NewsList? {
        return jsonAdapter.fromJson(json)
    }
}