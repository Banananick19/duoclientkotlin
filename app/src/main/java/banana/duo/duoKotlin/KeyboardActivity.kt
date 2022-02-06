package banana.duo.duoKotlin

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

class KeyboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoClientKotlinTheme {
                val line1Keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
                val line2Keys = listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")
                val line3Keys = listOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
                val line4Leys = listOf("z", "x", "c", "v", "b", "n", "m")
                val keyboardLines = listOf(line1Keys, line2Keys, line3Keys, line4Leys)
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    for (line in keyboardLines) {
                        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                            for (key in line) {
                                Button(onClick = {}) {
                                    Text(key)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting5(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    DuoClientKotlinTheme {
        val line1Keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        val line2Keys = listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")
        val line3Keys = listOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
        val line4Leys = listOf("z", "x", "c", "v", "b", "n", "m")
        val keyboardLines = listOf(line1Keys, line2Keys, line3Keys, line4Leys)
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            for (line in keyboardLines) {
                Row(horizontalArrangement = Arrangement.Center) {
                    for (key in line) {
                        Button({}) {
                            Text(key)
                        }
                    }
                }
            }
        }
    }
}