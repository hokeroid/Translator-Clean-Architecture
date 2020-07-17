package com.galichfactory.translatorcleanarchitecture.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class YandexTranslatorQueryBody(
    @SerializedName("folder_id")
    @Expose val folderId: String,

    @Expose val texts: List<String>,
    @Expose val targetLanguageCode: String
)