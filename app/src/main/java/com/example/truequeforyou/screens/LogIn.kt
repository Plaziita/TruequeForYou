@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.truequeforyou.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterial3Api
@Composable
fun InicioSesion() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the action when the "Done" button is pressed on the keyboard
                    }
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the action when the "Done" button is pressed on the keyboard
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            FirebaseAuth.getInstance()
                                .createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                                    if(it.isSuccessful){

                                    }else{

                                    }
                                }
                        }

                    }
                ) {
                    Text(
                        fontSize = 24.sp,
                        text = "Login"
                    )
                }
                Spacer(modifier = Modifier.width(64.dp))
                Button(
                    onClick = {

                    }
                ) {
                    Text(
                        fontSize = 24.sp,
                        text = "Sing up"
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun InicioSesionPreview() {
    InicioSesion()
}