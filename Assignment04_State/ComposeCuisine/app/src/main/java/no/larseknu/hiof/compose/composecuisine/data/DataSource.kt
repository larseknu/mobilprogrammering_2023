package no.larseknu.hiof.compose.composecuisine.data

import androidx.compose.ui.res.stringResource
import no.larseknu.hiof.compose.composecuisine.R
import no.larseknu.hiof.compose.composecuisine.model.Recipe

class Datasource() {
    fun loadRecipes(): List<Recipe> {
        return listOf<Recipe>(
            Recipe(1, "Pancakes", R.drawable.pancakes,
                "45min", true, 4.0f),
            Recipe(2, "Pizza", R.drawable.pizza,
                "30min", true, 4.0f),
            Recipe(3, "Blueberry Pancakes", R.drawable.pancakes,
                "45min", true, 2.0f),
            Recipe(4, "Hawaiian Pizza", R.drawable.pizza,
                "30min", true, 5.0f))
    }
}