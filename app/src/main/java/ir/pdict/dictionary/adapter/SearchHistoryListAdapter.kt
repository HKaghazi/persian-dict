package ir.pdict.dictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.pdict.dictionary.R
import ir.pdict.dictionary.database.model.SearchHistory

class SearchHistoryListAdapter: ListAdapter<SearchHistory, SearchHistoryListAdapter.SearchHistoryViewHolder>(
    SearchHistoryComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        return SearchHistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

    class SearchHistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textWord)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): SearchHistoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_history_list_item, parent, false)
                return SearchHistoryViewHolder(view)
            }
        }
    }

    class SearchHistoryComparator: DiffUtil.ItemCallback<SearchHistory>() {
        override fun areContentsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
            return oldItem.word == newItem.word
        }
    }
}