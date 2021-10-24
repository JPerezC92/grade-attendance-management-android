package com.example.gradeattendancemanagement.auth.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.gradeattendancemanagement.auth.types.Register
import com.example.gradeattendancemanagement.miscellaneous.hooks.useForm
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalContext
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter
import com.example.gradeattendancemanagement.miscellaneous.utils.Validator
import com.example.gradeattendancemanagement.miscellaneous.components.RoundedButton
import com.example.gradeattendancemanagement.miscellaneous.components.TransparentTextField


enum class RegisterFields(val value: String) {

    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    EMAIL("email"),
    PASSWORD("password"),
}

@Composable
fun RegisterScreen() {
    val localContext = LocalContext.current
    val router = LocalRouter.current

    val form =
        useForm(
            RegisterFields.FIRSTNAME.value to "",
            RegisterFields.LASTNAME.value to "",
            RegisterFields.EMAIL.value to "",
            RegisterFields.PASSWORD.value to ""
        )

    val registerRequestLoading = useLoading()

    val firstname = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current



    if (registerRequestLoading.isLoading) {
        SendRegisterRequest(
            register = Register(
                firstname = firstname.value,
                lastname = lastname.value,
                email = emailValue.value,
                password = passwordValue.value
            ), loading = registerRequestLoading
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                IconButton(
//                    onClick = {
//                        //TODO("BACK BOTTON")
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "back icon",
//                        tint = MaterialTheme.colors.primary
//                    )
//                }
                Text(
                    text = "Crear una cuenta",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransparentTextField(
                    textFieldValue = firstname,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                /////
                TransparentTextField(
                    textFieldValue = lastname,
                    textLabel = "Apellidos",
                    maxChar = 10,
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )



                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Correo",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )



                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                TransparentTextField(
                    textFieldValue = confirmPasswordValue,
                    textLabel = "Confirmar contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            // TODO("REGISTRATION")
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    text = "Registrarse",
                    displayProgressBar = registerRequestLoading.isLoading,
                    onClick = {
                        //TODO("OPERACION DE REGISTRARSE")


                        if (Validator(localContext).registerForm(
                                firstname.value,
                                lastname.value,
                                emailValue.value,
                                passwordValue.value,
                                confirmPasswordValue.value
                            )
                        ) {

                            registerRequestLoading.startLoading()
                        }
                    }
                )
                ClickableText(
                    text = buildAnnotatedString {
                        append("Ya tienes una cuenta? ")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Ingresar")
                        }
                    },
                    onClick = {
                        //TODO("BACK")
                        router.navToLogin()
                    }
                )

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

