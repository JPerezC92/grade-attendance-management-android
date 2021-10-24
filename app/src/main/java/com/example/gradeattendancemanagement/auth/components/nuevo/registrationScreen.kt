package com.example.gradeattendancemanagement.auth.components.nuevo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.gradeattendancemanagement.miscellaneous.components.RoundedButton
import com.example.gradeattendancemanagement.miscellaneous.components.TransparentTextField

@Composable
fun RegistrationScreen(){

    val nameValue = remember{ mutableStateOf("")}
    val emailValue = remember{ mutableStateOf("")}
    val surNameValue = remember{ mutableStateOf("")}
    val passwordValue = remember{ mutableStateOf("")}
    val confirmPasswordValue = remember{ mutableStateOf("")}

    var passwordVisibility by remember{ mutableStateOf(false)}
    var confirmPasswordVisibility by remember{ mutableStateOf(false)}

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        //TODO("BACK BOTTON")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text ="Crear una cuenta",
                    style =MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext ={
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                /////
                TransparentTextField(
                    textFieldValue = surNameValue,
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
                    displayProgressBar = false,
                    onClick = {
                        //TODO("OPERACION DE REGISTRARSE")
                    }
                )
                ClickableText(
                    text = buildAnnotatedString {
                        append("Ya tienes una cuenta?")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Ingresar")
                        }
                    },
                    onClick = {
                        //TODO("BACK")
                    }
                )

            }    

        }
        Spacer(modifier = Modifier.height(16.dp))
        /*
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Divider(
                    modifier = Modifier.width(24.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "OR",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Black
                    )
                )

                Divider(
                    modifier = Modifier.width(24.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Login With"
            )

        }

         */
    }
}