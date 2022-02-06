package banana.duo.duoKotlin.Client

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import androidx.compose.ui.platform.textInputServiceFactory
import banana.duo.Common.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.*

class ClientBluetooth : Client() {
    companion object {
        var m_myUUID: UUID = UUID.fromString("31444335-3731-324B-3531-B0227AE1D02F")
        var m_bluetoothSocket: BluetoothSocket? = null
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_address: String

        var instance: ClientBluetooth? = null

        fun createInstance() {
            instance = ClientBluetooth()
        }

        fun hasInstance(): Boolean {
            return instance != null
        }
    }

    override fun sendMessage(message: Message) {
        val out = message.toString() + "\n"
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.outputStream.write(out.toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun disconnect() {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun connect(): Boolean {
        var connectSuccess: Boolean = true
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    if (m_bluetoothSocket == null || !m_isConnected) {
                        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                        val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(
                            m_address
                        )
                        m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(
                            m_myUUID
                        )
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                        m_bluetoothSocket!!.connect()
                    }
                } catch (e: IOException)
                {
                    connectSuccess = false
                    e.printStackTrace()
                }

                if (!connectSuccess) {
                    Log.i("data", "couldn't connect")
                } else {
                    m_isConnected = true
                }
            }
        }
        return connectSuccess
    }

}