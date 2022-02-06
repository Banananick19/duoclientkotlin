package banana.duo.duoKotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import banana.duo.duoKotlin.ui.theme.DuoClientKotlinTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class ConnectWiFiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoClientKotlinTheme {
                // A surface container using the 'background' color from the the
                val ip = remember { mutableStateOf("")}
                val port = remember { mutableStateOf("")}
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(modifier = Modifier.padding(20.dp, 20.dp),horizontalArrangement = Arrangement.Center) {
                            TextField(ip.value, {ip.value = it}, label = { Text(text = "IP") },
                                placeholder = { Text(text = "Введите ip") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                        }

                        Row(modifier = Modifier.padding(20.dp, 20.dp),horizontalArrangement = Arrangement.Center) {
                            TextField(port.value, {port.value = it}, label = { Text(text = "Порт") },
                                placeholder = { Text(text = "Введите порт") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }

                        Row(modifier = Modifier.padding(20.dp, 20.dp),horizontalArrangement = Arrangement.Center) {
                            Button({onSubmit(ip.value, port.value)}) {
                                Text("Подключиться")
                            }
                        }
                    }
                }
            }
        }
    }

    fun onSubmit(ip: String, port: String) {
        //TODO: коннект
        val intent = Intent(this, ChoseDeviceActivity::class.java)
        intent.putExtra("ip", ip)
        intent.putExtra("port", port)
        startActivity(intent)
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    DuoClientKotlinTheme {
        Greeting2("Android")
    }
}