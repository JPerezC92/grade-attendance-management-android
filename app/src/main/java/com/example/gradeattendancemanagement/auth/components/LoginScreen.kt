package com.example.gradeattendancemanagement.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gradeattendancemanagement.R.*
import com.example.gradeattendancemanagement.auth.types.Credentials
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter
import com.example.gradeattendancemanagement.miscellaneous.components.RoundedButton
import com.example.gradeattendancemanagement.miscellaneous.components.TransparentTextField

enum class LoginFields(val value: String) {
    EMAIL("email"),
    PASSWORD("password"),
}

@Composable
fun LoginScreen() {
    val router = LocalRouter.current


    val emailValue = rememberSaveable { mutableStateOf("test2@gmail.com") }
    val passwordValue = rememberSaveable { mutableStateOf("123456aA") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val loginRequestLoading = useLoading()

    if (loginRequestLoading.isLoading === true) {
        SendLoginRequest(
            loading = loginRequestLoading,
            credentials = Credentials(
                email = emailValue.value,
                password = passwordValue.value
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(40.dp)


    ) {

        Image(
            painter = painterResource(id = drawable.logo_senati),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(vertical = 40.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,


            ) {
            ConstraintLayout {

                val (surface, fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8,

                        bottomEndPercent = 8,
                        bottomStartPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Text(
                            text = "Ingresar",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Correo",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )

                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()

                                        //TODO("LOGIN")
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )
//                            Text(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                text = "Forgot password?",
//                                style = MaterialTheme.typography.body1,
//                                textAlign = TextAlign.End
//
//                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            RoundedButton(
                                text = "Iniciar sesión",
                                displayProgressBar = loginRequestLoading.isLoading,
                                onClick = {
                                    loginRequestLoading.startLoading()
                                }
                            )

                            ClickableText(
                                text = buildAnnotatedString {
                                    append("¿Aún no tienes una cuenta? ")
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append("Registrarse")
                                    }
                                }
                            ) {
                                router.navToRegister()
                            }
                        }
                    }
                }
//                FloatingActionButton(
//                    modifier = Modifier
//                        .size(72.dp)
//                        .constrainAs(fab) {
//                            top.linkTo(surface.top, margin = (-36).dp)
//                            end.linkTo(surface.end, margin = (-36).dp)
//                        },
//                    backgroundColor = MaterialTheme.colors.primary,
//                    onClick = {}
//                ) {
//                    Icon(
//                        modifier = Modifier.size(42.dp),
//                        imageVector = Icons.Default.ArrowForward,
//                        contentDescription = "Forward Icon",
//                        tint = Color.White
//                    )
//
//                }
            }
        }
    }
}

