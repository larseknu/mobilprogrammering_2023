package no.larseknu.hiof.compose.composecuisine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.larseknu.hiof.compose.composecuisine.data.Datasource
import no.larseknu.hiof.compose.composecuisine.model.Recipe
import no.larseknu.hiof.compose.composecuisine.ui.theme.ComposeCuisineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCuisineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}

@Composable
fun RecipeApp() {
    val recipes = Datasource().loadRecipes()

    RecipeList(recipes = recipes,
        onRecipeClick =  {
                recipe -> Log.d("RecipeApp", "Recipe clicked: ${recipe.title}")
        },
        onFavouriteToggle = {
                recipe -> Log.d("RecipeApp", "Toggle ${recipe.title} as favourite.")
        }
    )
}

@Composable
fun RecipeList(recipes: List<Recipe>,
               onRecipeClick: (Recipe) -> Unit,
               onFavouriteToggle: (Recipe) -> Unit,
               modifier: Modifier = Modifier) {
    LazyColumn(userScrollEnabled = true,
        modifier = modifier) {
        items(recipes) { recipe ->
            RecipeCard(
                recipe,
                onRecipeClick = onRecipeClick,
                onFavouriteToggle = onFavouriteToggle
            )
        }
    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClick: (Recipe) -> Unit,
    onFavouriteToggle: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavourite by remember { mutableStateOf(recipe.isFavourite) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                Log.d("RecipeCard", "Card clicked")
                onRecipeClick(recipe)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = recipe.imageResourceId),
                contentDescription = null, // consider providing appropriate content description
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_timer), contentDescription = null)
                    Text(text = recipe.cookingTime)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    StarRating(rating = recipe.rating)
                    Text(text = String.format("%.1f", recipe.rating))
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconToggleButton(checked = isFavourite,
                    onCheckedChange = {
                        isFavourite = !isFavourite
                        onFavouriteToggle(recipe)
                    })  {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = modifier.graphicsLayer {
                            scaleX = 1.3f
                            scaleY = 1.3f
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun StarRating(rating: Float) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Outlined.Star else ImageVector.vectorResource(
                    id = R.drawable.ic_outline_star
                ),
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeAppPreview() {
    ComposeCuisineTheme {
        RecipeApp()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    ComposeCuisineTheme {
        RecipeCard(Recipe(1, stringResource(id = R.string.pancakes), R.drawable.pancakes,
            "45min", true, 4.0f),
            onRecipeClick = {},
            onFavouriteToggle = {})
    }
}