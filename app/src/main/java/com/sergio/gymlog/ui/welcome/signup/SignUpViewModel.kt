package com.sergio.gymlog.ui.welcome.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.sergio.gymlog.data.authentication.FirebaseAuthenticationService
import com.sergio.gymlog.data.model.FirebaseResource
import com.sergio.gymlog.ui.welcome.UserAccesUiState
import com.sergio.gymlog.util.helper.LoginAndSignUpHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseAuthenticationService,
    private val loginAndSignUpHelper: LoginAndSignUpHelper
    ) : ViewModel(){

    private val _uiState : MutableStateFlow<UserAccesUiState> = MutableStateFlow(UserAccesUiState())
    val uiState get() = _uiState.asStateFlow()


    fun signUpWithEmailAndPassword(email : String, password : String){

        signUp { firebaseRepository.signUpWithEmailAndPassword(email, password) }

    }

    fun signUpWithGoogle(task : Task<GoogleSignInAccount>){

        signUp { firebaseRepository.loginWithGoogleAccount(task) }

    }

    private fun signUp( signUpMethod : suspend () -> FirebaseResource<Any>){

        viewModelScope.launch {

            loginAndSignUpHelper.acces(signUpMethod, _uiState)

        }

    }

    fun errorMessageShown(){

        loginAndSignUpHelper.errorMessageShown(_uiState)

    }

}