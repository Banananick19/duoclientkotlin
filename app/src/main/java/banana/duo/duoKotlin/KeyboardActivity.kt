package banana.duo.duoKotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banana.duo.Common.ActionType
import banana.duo.Common.Message
import banana.duo.duoKotlin.Client.currentClient
import banana.duo.duoKotlin.ui.theme.DuoClientKotlinTheme
import java.util.*

class KeyboardActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoClientKotlinTheme {
                val keys = listOf(
                    listOf("escape", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
                    listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
                    listOf("CAPS LOCK", "a", "s", "d", "f", "g", "h", "j", "k", "l"),
                    listOf("shift", "z", "x", "c", "v", "b", "n", "m"),
                    listOf("ctrl", "windows", "space", "alt", "ctrl", "enter")
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    for (line in keys) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (key in line) {
                                Button(modifier = Modifier.size(40.dp, 40.dp),
                                        onClick = {
                                            currentClient?.sendMessage(
                                                Message(
                                                    ActionType.KeyPress, mapOf(
                                                        Pair(
                                                            "code",
                                                            key.uppercase(Locale.getDefault())
                                                        )
                                                    )
                                                )
                                            )
                                        }
                                ) {
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            for (line in keyboardLines) {
                Row(horizontalArrangement = Arrangement.Center) {
                    for (key in line) {
                        Button(modifier = Modifier.size(40.dp, 40.dp), onClick = {}) {
                            Text(key)
                        }
                    }
                }
            }
        }
    }
}