/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package no.larseknu.hiof.compose.playingwithfirebase.screen.sign_up

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import no.larseknu.hiof.compose.playingwithfirebase.R

@Composable
fun SignUpScreen(
  loggedIn: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val isAnonymous by viewModel.isAnonymous.collectAsState(initial = true)

  val fieldModifier = Modifier
    .fillMaxWidth()
    .padding(16.dp, 4.dp)

  if (isAnonymous) {
    Column(
      modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (uiState.errorMessage != 0)
        Text(text = stringResource(id = uiState.errorMessage))

      EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
      PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)

      RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

      Row {
        Button(
          onClick = { viewModel.onLoginClick(loggedIn) },
          modifier = Modifier
            .padding(16.dp, 8.dp),
        ) {
          Text(text = stringResource(R.string.login), fontSize = 16.sp)
        }
        Button(
          onClick = { viewModel.onSignUpClick(loggedIn) },
          modifier = Modifier
            .padding(16.dp, 8.dp),
        ) {
          Text(text = stringResource(R.string.create_account), fontSize = 16.sp)
        }
      }
    }
  }
  else {
    Button(
      onClick = { viewModel.onSignOutClick() },
      modifier = Modifier
        .padding(16.dp, 8.dp),
    ) {
      Text(text = stringResource(R.string.sign_out), fontSize = 16.sp)
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  OutlinedTextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(stringResource(R.string.email)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
  )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  PasswordField(value, R.string.password, onNewValue, modifier)
}

@Composable
fun RepeatPasswordField(
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  PasswordField(value, R.string.repeat_password, onNewValue, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
  value: String,
  @StringRes placeholder: Int,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var isVisible by remember { mutableStateOf(false) }

  val icon =
    if (isVisible) painterResource(R.drawable.ic_visibility_on)
    else painterResource(R.drawable.ic_visibility_off)

  val visualTransformation =
    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

  OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(text = stringResource(placeholder)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
    trailingIcon = {
      IconButton(onClick = { isVisible = !isVisible }) {
        Icon(painter = icon, contentDescription = "Visibility")
      }
    },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    visualTransformation = visualTransformation
  )
}