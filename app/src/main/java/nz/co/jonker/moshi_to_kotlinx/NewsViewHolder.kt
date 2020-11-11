package nz.co.jonker.moshi_to_kotlinx

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    private val textView: TextView = itemView.findViewById(R.id.news_item_text)

    fun bind(string: String) {
        textView.text = string
    }
}