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

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import no.larseknu.hiof.compose.playingwithfirebase.R
import no.larseknu.hiof.compose.playingwithfirebase.common.ext.isValidEmail
import no.larseknu.hiof.compose.playingwithfirebase.common.ext.isValidPassword
import no.larseknu.hiof.compose.playingwithfirebase.service.AccountService
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val accountService: AccountService,
) : ViewModel() {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  val isAnonymous = accountService.currentUser.map { it.isAnonymous }

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onRepeatPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(repeatPassword = newValue)
  }

  fun onLoginClick(loggedIn: () -> Unit) {
    if (!email.isValidEmail()) {
      uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
      return
    }

    else if (!password.isValidPassword()) {
      uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
      return
    }

    viewModelScope.launch {
     try {
        accountService.authenticate(email, password) { error ->
          if (error == null)
            loggedIn()
        }
      }
      catch(e: Exception) {
        uiState.value = uiState.value.copy(errorMessage = R.string.could_not_log_in)
      }
    }
  }

  fun onSignUpClick(loggedIn: () -> Unit) {
    if (!email.isValidEmail()) {
      uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
      return
    }

    else if (!password.isValidPassword()) {
      uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
      return
    }

    else if (!(password == uiState.value.repeatPassword)) {
      uiState.value = uiState.value.copy(errorMessage = R.string.password_match_error)
      return
    }

    viewModelScope.launch {
      try {
        accountService.linkAccount(email, password) { error ->
          if (error == null)
            loggedIn()
         }
      }
      catch(e: Exception) {
        uiState.value = uiState.value.copy(errorMessage = R.string.could_not_create_account)
      }
    }
  }

  fun onSignOutClick() {
    viewModelScope.launch {
      accountService.signOut()
    }
  }
}
