package banana.duo.duoKotlin

import android.R
import android.app.Activity
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import banana.duo.duoKotlin.ui.theme.DuoClientKotlinTheme
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.focus.focusModifier
import banana.duo.duoKotlin.Client.ClientBluetooth
import banana.duo.duoKotlin.Client.currentClient
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class ConnectBluetoothActivity : ComponentActivity() {

    private var m_bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var m_pairedDevices: Set<BluetoothDevice>
    private val REQUEST_ENABLE_BLUETOOTH = 1

    var listOfDevice: List<BluetoothDevice> by mutableStateOf(listOf())

    companion object {
        val EXTRA_ADDRESS: String = "Device_address"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(m_bluetoothAdapter == null) {
            Toast.makeText(this, "this device doesn't support bluetooth", Toast.LENGTH_LONG).show()
            return
        }
        if(!m_bluetoothAdapter!!.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothLauncher.launch(enableBluetoothIntent)
        }

        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Button({listOfDevice = pairedDeviceList()}) {
                        Text("Обновить")
                    }
                }

                    for (device in listOfDevice) {
                        Row() {
                            Button({connectDevice(device.address)}) {
                                Text(device.address)
                            }
                        }
                    }

            }
        }

    }

    private fun connectDevice(address: String) {
        val intent = Intent(this, ChoseDeviceActivity::class.java)
        ClientBluetooth.m_address = address
        ClientBluetooth.createInstance()
        val connected = ClientBluetooth.instance?.connect()
        if (!connected!!) {
            Toast.makeText(this, "Не удалось подключиться", Toast.LENGTH_LONG).show()
            return
        }
        currentClient = ClientBluetooth.instance
        startActivity(intent)
    }

    private fun pairedDeviceList(): List<BluetoothDevice> {
        m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
        val list : ArrayList<BluetoothDevice> = ArrayList()

        if (!m_pairedDevices.isEmpty()) {
            for (device: BluetoothDevice in m_pairedDevices) {
                list.add(device)
                Log.i("device", ""+device)
            }
        } else {
            Toast.makeText(this, "no paired bluetooth devices found", Toast.LENGTH_LONG).show()
        }
        return list
    }



    var enableBluetoothLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == REQUEST_ENABLE_BLUETOOTH) {
            if (result.resultCode == Activity.RESULT_OK) {
                if (m_bluetoothAdapter!!.isEnabled) {
                    Toast.makeText(this, "Bluetooth has been enabled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Bluetooth has been disabled", Toast.LENGTH_LONG).show()
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Bluetooth enabling has been canceled", Toast.LENGTH_LONG).show()
            }
        }
    }


}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    DuoClientKotlinTheme {
        Greeting3("Android")
    }
}