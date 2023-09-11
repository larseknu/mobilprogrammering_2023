package no.larseknu.hiof.compose.playingwithnavigation_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import no.larseknu.hiof.compose.playingwithnavigation_test.ui.HomeScreen
import no.larseknu.hiof.compose.playingwithnavigation_test.ui.LoginScreen
import no.larseknu.hiof.compose.playingwithnavigation_test.ui.theme.PlayingWithNavigation_TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithNavigation_TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationApp()
                }
            }
        }
    }
}

enum class Screen {
    Login,
    Home
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentRoute by rememberUpdatedState(newValue = navController.currentBackStackEntryAsState().value?.destination?.route ?: Screen.Login.name)

    Scaffold(
        topBar = {
            TopAppBar( title = { Text(text = currentRoute) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ))
        }
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.Login.name,
            modifier = modifier.fillMaxSize()) {
            composable(Screen.Login.name) {
                LoginScreen(login = {navController.navigate(Screen.Home.name)})
            }
            composable(Screen.Home.name) {
                HomeScreen(modifier.padding(innerPadding))
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlayingWithNavigation_TestTheme {
        NavigationApp()
    }
}