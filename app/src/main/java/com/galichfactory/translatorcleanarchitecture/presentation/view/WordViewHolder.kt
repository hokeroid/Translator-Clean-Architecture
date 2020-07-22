package com.galichfactory.translatorcleanarchitecture.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.domain.Word

class WordViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.word_item, parent, false)) {
    private var originalTextView: TextView? = null
    private var translatedTextView: TextView? = null
    private var originalLanguageTextView: TextView? = null
    private var translatedLanguageTextView: TextView? = null

    init {
        originalTextView = itemView.findViewById(R.id.originalText)
        translatedTextView = itemView.findViewById(R.id.translatedText)
        originalLanguageTextView = itemView.findViewById(R.id.originalLanguage)
        translatedLanguageTextView = itemView.findViewById(R.id.translatedLanguage)
    }

    fun bind(word: Word) {
        originalTextView?.text = word.originalText
        translatedTextView?.text = word.translatedText
        originalLanguageTextView?.text = word.originalLanguage
        translatedLanguageTextView?.text = word.translatedLanguage
    }
}