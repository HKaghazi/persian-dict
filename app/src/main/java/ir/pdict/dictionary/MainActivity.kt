package ir.pdict.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.pdict.dictionary.adapter.SearchHistoryListAdapter
import ir.pdict.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var searchInput: EditText
    private lateinit var searchHistoryRV: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory((application as DictionaryApplication).searchHistoryRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding
        searchInput = binding.searchInput
        searchHistoryRV = binding.searchHistoryRecyclerView

        val adapter = SearchHistoryListAdapter()
        searchHistoryRV.adapter = adapter
        searchHistoryRV.layoutManager = LinearLayoutManager(this)

        mainActivityViewModel.latestSearch.observe(this) { result ->
            result.let { adapter.submitList(it) }
        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.d("test", s.toString())
            }

        })

        searchInput.requestFocus()

    }


}