package no.larseknu.hiof.compose.composecuisine.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipe(
    val id: Int,
    val title: String,
    @DrawableRes val imageResourceId: Int,
    val cookingTime: String,
    var isFavourite: Boolean,
    val rating: Float
)
