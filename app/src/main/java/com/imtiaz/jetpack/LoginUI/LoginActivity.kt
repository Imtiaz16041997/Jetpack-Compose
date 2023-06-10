package com.imtiaz.jetpack.LoginUI


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.imtiaz.jetpack.ui.theme.JetPackTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imtiaz.jetpack.MainActivity
import com.imtiaz.jetpack.R

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            JetPackTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }
    }

    private fun logged(username: String, password:String){
        if(username == "imtiaz" && password == "12345"){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"wrong credential",Toast.LENGTH_SHORT).show()

        }
    }
    
    @Composable
    fun LoginScreen(){

        val username = remember{
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }

        var passwordVisible by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,

        ) {
            Text(text = "Welcome! ", fontSize = 25.sp, color = Color.Blue, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Text(text = "To The Login Page", fontSize = 25.sp,color = Color.LightGray, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                username.value = it
            },
                leadingIcon = {
                    Icon(Icons.Default.Person , contentDescription = "person" )
                },
                label = {
                    Text(text = "username")
                },
                placeholder = {
                    Text(text = "enter username")
                },

                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
           )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Info , contentDescription = "password" )
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "enter password")
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
//                visualTransformation = PasswordVisualTransformation()
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible },modifier = Modifier.padding(end = 8.dp)) {
                        Icon(
                            painter = painterResource(if (passwordVisible) R.drawable.eye_on else R.drawable.eye_icon),
                            contentDescription = if (passwordVisible) "Hide Password" else "Show Password",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

            )
            
            OutlinedButton(onClick = {logged(username.value,password.value)},
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)

            ) {
                Text(text = "Login")
            }
        }
    }
    
}