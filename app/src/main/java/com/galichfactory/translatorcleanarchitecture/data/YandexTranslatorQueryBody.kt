package com.galichfactory.translatorcleanarchitecture.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

object YandexTranslatorQueryBody {
    @SerializedName("folder_id")
    @Expose
    lateinit var folderId: String

    @Expose
    lateinit var texts: List<String>

    @Expose
    lateinit var targetLanguageCode: String
}