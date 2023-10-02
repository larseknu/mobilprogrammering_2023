package larseknu.hiof.playingwithviewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleScreen(simpleViewModel: SimpleViewModel = viewModel()) {
    val name by simpleViewModel.name.collectAsState()

    var newName by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = newName,
            onValueChange = {newName = it},
            label = { Text("Update Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { simpleViewModel.updateName(newName) }) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Name: $name", fontSize = 24.sp)
    }

}