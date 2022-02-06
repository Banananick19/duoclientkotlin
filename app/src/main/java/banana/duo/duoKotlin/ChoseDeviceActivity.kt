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

class ChoseDeviceActivity : ComponentActivity() {
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
                        Row(
                            modifier = Modifier.padding(20.dp, 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button({ startMouseActivity() }) {
                                Text("Мышь")
                            }
                        }

                        Row(
                            modifier = Modifier.padding(20.dp, 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button({startKeyboardActivity()}) {
                                Text("Клавиатура")
                            }
                        }

                        Row(
                            modifier = Modifier.padding(20.dp, 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button({}) {
                                Text("Клав+мышь")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun startMouseActivity() {
        startActivity(Intent(this, MouseActivity::class.java))
    }

    private fun startKeyboardActivity() {
        startActivity(Intent(this, KeyboardActivity::class.java))
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    DuoClientKotlinTheme {
        Greeting4("Android")
    }
}