package com.galichfactory.translatorcleanarchitecture.repository

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.data.*
import com.galichfactory.translatorcleanarchitecture.domain.Dictionary
import com.galichfactory.translatorcleanarchitecture.domain.Translation
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl : Repository {
    private fun mapDictionaryToDbWords(dictionary: Dictionary): List<DbWord> {
        val dbWords: MutableList<DbWord> = mutableListOf()
        for (word in dictionary.words) {
            val dbTranslations: MutableList<DbTranslation> = mutableListOf()
            for (translation in word.translations) {
                dbTranslations.add(
                    DbTranslation(
                        language = translation.language,
                        translation = translation.translation
                    )
                )
            }

            val dbWord = DbWord(translations = dbTranslations, wordName = word.wordName)
            dbWords.add(dbWord)
        }
        return dbWords
    }

    private fun mapDbWordsToDictionary(dbWords: List<DbWord>) : Dictionary {
        val dictionary = Dictionary(mutableListOf())

        for (dbWord in dbWords) {
            val translations: MutableList<Translation> = mutableListOf()
            for (dbTranslation in dbWord.translations) {
                translations.add(
                    Translation(
                        language = dbTranslation.language,
                        translation = dbTranslation.translation
                    )
                )
            }

            val word = Word(translations = translations, wordName = dbWord.wordName)
            dictionary.words.add(word)
        }
        return dictionary
    }

    override fun getDictionary(context: Context): Dictionary {
        val db = AppDatabase.create(context)

        return mapDbWordsToDictionary(db.wordDao().getAll())
    }

    override fun setDictionary(context: Context, dictionary: Dictionary) {
        val db = AppDatabase.create(context)

        val dbWords = mapDictionaryToDbWords(dictionary)
        for (dbWord in dbWords) {
            db.wordDao().insert(dbWord)
        }
    }

    override fun getTranslation(word: String, language: String): Translation? {
        var translation: Translation? = null
        val queryBody = YandexTranslatorQueryBody
        val token =
            "Bearer CggVAgAAABoBMxKABA_j6Zqq7t8YFeao11tjI3vASz1KzfaSqhBoT59ylRfJH4m0u9bT9xU3mCnFmp_ZozoIUkdI2qNwuWLAL_LEwl45Mv8tqFgJ53bX8xtIKauyBYbXPRzVjoqHIY3qWmpYaJuCKeRECivkx-0rjw89X-Bz8RUhF7JiZMEhWe0arQggnlGS4lJOR92M0b4IcHC8HDyGWKSV5Zj5KoWm-XMmy5bOKBHhMSlLIITk0wpTCqsoKsCFiGheFUsbp0VPIyxpsu34TdTzmjoXTI_zv0xQ66w4Q0ph-tGT42tEG-RXI_X7-GWKrmnZJfuWIV-LlLvTK3QTciRlsfUWVbx620WFHkAkDx73DSHYcxavNmK-eBwxg-eqAoyiFuRruiRAVg6rCdC4VQkE2jlGoROnSmpYHWioA1eFJ3Nd5_PiWDjGxo8Z--FxxwQBk5ZHxjRaE2Mp3hCA2kAK8pXO4eDHEiDNpiodu_L6tgLXIvbdsDckzb8TneCHt-rMUkZeqKZmcxfzzBgJ9PccCbH7hg3AQZhUl6oZZsx7PGN2tUp9dQGcX7Yw7vzIhBbJ6siql9WfU6gOpjdyohpZJNB36oDBOC_m0Li2BgfVXsEDXAIdL56s6vTe-QO40rPScv5e6BHOBjFhJGqRdtrFYWh0aJWdis5eYX3rtA8HOzGof5CVeyFLbarrGiQQru6_-AUY7r_C-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8=-XcuO1yKYwpSwA7yrp6mEGAOVxdeIc7KF2wUB6C2fvpSQ-yg9atgVDZv1ukSx8eV0azoEtXpduSeOnpPFErl_nyVO_hcUT-IxcsiFtY4oSfDzLKrYAXAMgHAFcM8dACcYw9y--mhkGLgrN9E6NKRhQ4ZL0pT8WwCamx6kwDrge_y8gJcZBCAeyo9m15p9_322vCwou04SL1yqyBLDn6YuWrfvw_uTK05_hdXKZH_iT9ksOmkBshvAtUVeFIdFPp1JaQLGDJCH_aLIOyQbNi85kRoYTfbdhwwBuCsis254brBESpX8NpYdzEZlLJbR7IYIbgW4CFf-92si-ezvZhoqRgEVLflJjXD3aBJ_SnmujPeVDW1TrEoSzd3Vzs-6i3sZn99j87yPcuGoo1EatXvtvNgf2gOEDgeXwUWBOcPzgV0NK8pv0BsxZV36lH2PVuC3Pv9by4GiQQ8ve1-AUYssm4-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="
        queryBody.folderId = "b1g8hrtq42kmn7g8a469"
        queryBody.texts = listOf(word)
        queryBody.targetLanguageCode = language
        val result =
            YandexTranslatorApiService.create().getTranslation(
                body = queryBody,
                headers = mapOf("Authorization" to token)
            )

        result.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                print("result is ${result.translations[0].text}")
                val language = result.translations[0].detectedLanguageCode
                val text = result.translations[0].text
                translation = Translation(language = language, translation = text)
            },
                { error ->
                    error.printStackTrace()
                })

        return translation
    }
}