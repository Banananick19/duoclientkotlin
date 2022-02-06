package banana.duo.duoKotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import banana.duo.duoKotlin.ui.theme.DuoClientKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoClientKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
                            Button({ startConnectWiFiActivity() }) {
                                Text("WI-FI")
                            }
                        }

                        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
                            Button({ startConnectBluetoothActivity() }) {
                                Text("Bluetooth")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun startConnectWiFiActivity() {
        startActivity(Intent(this, ConnectWiFiActivity::class.java))
    }

    private fun startConnectBluetoothActivity() {
        startActivity(Intent(this, ConnectBluetoothActivity::class.java))
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DuoClientKotlinTheme {
        Greeting("Android")
    }
}