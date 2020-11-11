package nz.co.jonker.moshi_to_kotlinx

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.jonker.moshi_to_kotlinx.data.ListItem

class NewsAdapter(private val data: List<ListItem>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(parent.inflate(R.layout.item_news_data))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position].toString())
    }
}
