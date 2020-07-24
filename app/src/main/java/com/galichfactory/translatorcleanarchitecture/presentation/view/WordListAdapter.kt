package com.galichfactory.translatorcleanarchitecture.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.galichfactory.translatorcleanarchitecture.domain.Word

class WordListAdapter() : RecyclerView.Adapter<WordViewHolder>() {
    private var words: List<Word> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WordViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }
}