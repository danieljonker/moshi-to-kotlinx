package nz.co.jonker.moshi_to_kotlinx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import nz.co.jonker.moshi_to_kotlinx.data.ListItem
import nz.co.jonker.moshi_to_kotlinx.data.*
import nz.co.jonker.moshi_to_kotlinx.json.NewsFeedAdapter
import nz.co.jonker.moshi_to_kotlinx.json.NewsFeedImageAdapter
import nz.co.jonker.moshi_to_kotlinx.json.MoshiWrapper
import nz.co.jonker.moshi_to_kotlinx.json.NewsListJsonWrapper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        val jsonWrapper: NewsListJsonWrapper = MoshiWrapper(moshi)

        val json =
            resources.openRawResource(R.raw.sample_json).bufferedReader().use { it.readText() }
        val newsList = jsonWrapper.fromJson(json)
        newsList?.let {
            title = newsList.name
            recycler.adapter = NewsAdapter(newsList.data)
        }
    }
}

private val polymorphicJsonAdapterFactory = PolymorphicJsonAdapterFactory.of(
    ListItem::class.java,
    "type"
)
    .withSubtype(Article::class.java, "story")
    .withSubtype(Video::class.java, "video")
    .withDefaultValue(Invalid)

@VisibleForTesting
val moshi =
    Moshi.Builder()
        .add(NewsFeedImageAdapter())
        .add(NewsFeedAdapter())
        .add(polymorphicJsonAdapterFactory)
        .add(KotlinJsonAdapterFactory())
        .build()

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)
