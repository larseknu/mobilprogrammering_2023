package larseknu.hiof.playingwithlists

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import larseknu.hiof.playingwithlists.model.Superhero
import larseknu.hiof.playingwithlists.ui.theme.PlayingWithListsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroList()
                }
            }
        }
    }
}

@Composable
fun SimpleList(modifier: Modifier = Modifier) {
    val superheroes = listOf("Superman",
        "Batman",
        "Wonder Woman",
        "Iron Man")

    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start) {
        items(superheroes) { superhero ->
            Row {
                Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Superhero")
                Text(text = superhero)
            }
        }
    }
}

@Composable
fun SuperheroList(modifier: Modifier = Modifier) {
    val superheroesDC = listOf(
        Superhero("Superhero","Clark Kent", Icons.Default.Face),
        Superhero("Batman","Bruce Banner", Icons.Default.AccountBox),
        Superhero("Wonder Woman", "Diana Prince", Icons.Default.Favorite))

    val superheroesMarvel = listOf(
        Superhero("Iron Man", "Tony Stark", Icons.Default.Build),
        Superhero("Captain America", "Steve Rogers", Icons.Default.AddCircle))

    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Text(text = "Superheros DC", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        items(superheroesDC) { superhero ->
            SuperheroRow(superhero = superhero)
        }

        item {
            Text("Superheros Marvel", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        items(superheroesMarvel) { superhero ->
            SuperheroRow(superhero = superhero)
        }
    }
}

@Composable
fun SuperheroRow(superhero: Superhero, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.clickable { Log.d("SuperheroRow", "${superhero.name} Clicked") }) {
        Icon(imageVector = superhero.icon,
            contentDescription = "Superhero")
        Text(text = "${superhero.name} - ${superhero.alterEgo}")
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    PlayingWithListsTheme {
        SuperheroList()
    }
}