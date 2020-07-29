package com.galichfactory.translatorcleanarchitecture.presentation.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.domain.Word
import kotlinx.android.synthetic.main.word_item.view.*

class WordViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.word_item, parent, false)) {
    fun bind(word: Word) {
        itemView.originalText?.text = word.originalText
        itemView.translatedText?.text = word.translatedText
        itemView.originalLanguage?.text = word.originalLanguage
        itemView.translatedLanguage?.text = word.translatedLanguage
    }
}