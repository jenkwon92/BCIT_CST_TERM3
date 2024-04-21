package com.bcit.myminiapp.ui.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.bcit.myminiapp.data.Amiibo
import com.bcit.myminiapp.data.AmiiboRepository
import com.bcit.myminiapp.data.LocalUser

class AmiiboState(private val amiiboRepository: AmiiboRepository) {
    var amiibolist = mutableStateListOf<Amiibo>()

    var favorites : MutableList<Amiibo> = mutableListOf()

    suspend fun getAmiibo(){
        amiibolist.also {
            it.clear()
            it.addAll(amiiboRepository.getAmiibo().amiibo)
        }
    }
    fun refreshFavoriteList(favoriteAmiiboNames: String, amiiboState: AmiiboState) {
        if (favoriteAmiiboNames.isNotEmpty()) {
            favorites.clear()
            val names: List<String> = "[\\w]+".toRegex().findAll(favoriteAmiiboNames)
                .map { it.value }
                .toList()
            for (name in names) {
                findAmiiboByName(name, amiiboState.amiibolist)?.let { amiibo ->
                    favorites.add(amiibo)
                }
            }
        }
    }

    fun addFavoriteName(user: LocalUser, amiibo: Amiibo) {
        if (!user.favoriteAmiiboNames.contains(amiibo.name)) {
            val newFavoriteAmiiboNames = "${amiibo.name}, ${user.favoriteAmiiboNames}"
            user.favoriteAmiiboNames = newFavoriteAmiiboNames

            findAmiiboByName(amiibo.name, amiibolist)?.let { favorites.add(it) }
            updateFavoriteName(user)
        }
    }

    fun removeFavoriteName(user: LocalUser, name: String) {
        val names: List<String> = "[\\w]+".toRegex().findAll(user.favoriteAmiiboNames)
            .map { it.value }
            .toList()
        val newFavoriteAmiiboNames = names.filter { it != name }.joinToString(", ")
        user.favoriteAmiiboNames = newFavoriteAmiiboNames
        updateFavoriteName(user)
    }

    private fun updateFavoriteName(user:LocalUser) {
        amiiboRepository.updateFavouriteNames(user)
    }

    fun findAmiiboByName(name: String, amiibolist: MutableList<Amiibo>): Amiibo? {
        return amiibolist.find { it.name == name }
    }
}