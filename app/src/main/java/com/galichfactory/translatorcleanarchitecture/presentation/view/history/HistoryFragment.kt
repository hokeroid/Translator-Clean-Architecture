package com.galichfactory.translatorcleanarchitecture.presentation.view.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.HistoryPresenter
import kotlinx.android.synthetic.main.fragment_history.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class HistoryFragment : MvpAppCompatFragment(), HistoryView {
    @Inject
    lateinit var presenterProvider: Provider<HistoryPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val wordListAdapter =
        WordListAdapter()

    override fun showWords(words: List<Word>) {
        wordListAdapter.words = words
    }

    override fun onAttach(context: Context) {
        TranslatorApp.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordListAdapter
        }

        backButton.setOnClickListener {
            presenter.onBackButtonClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("historyFragment", "History Fragment onDestroy()")
    }
}