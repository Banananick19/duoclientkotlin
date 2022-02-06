package banana.duo.duoKotlin

import android.R.attr
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import banana.duo.Common.Message
import banana.duo.duoKotlin.ConnectBluetoothActivity.Companion.EXTRA_ADDRESS
import banana.duo.duoKotlin.databinding.ActivityMouseBinding
import com.google.gson.Gson
import io.github.controlwear.virtual.joystick.android.JoystickView
import io.github.controlwear.virtual.joystick.android.JoystickView.OnMoveListener
import java.io.IOException
import java.util.*
import android.R.attr.angle
import banana.duo.Common.ActionType


class MouseActivity : AppCompatActivity() {

    companion object {
        var m_myUUID: UUID = UUID.fromString("31444335-3731-324B-3531-B0227AE1D02F")
        var m_bluetoothSocket: BluetoothSocket? = null
        lateinit var m_progress: ProgressDialog
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_address: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m_address = intent.getStringExtra(EXTRA_ADDRESS)!!

        ConnectToDevice(this).execute()
        setContentView(R.layout.activity_mouse)
        setListeners()
    }

    fun setListeners() {
        findViewById<JoystickView>(R.id.mouse_joystick).setOnMoveListener { angle, strength ->
            val x = (Math.cos(Math.toRadians(angle.toDouble())) * strength).toInt() / 10
            val y = (-(Math.sin(Math.toRadians(angle.toDouble())) * strength)).toInt() / 10
            sendCommand(Message(ActionType.MouseMove, mapOf(Pair("x", x.toString()), Pair("y", y.toString()))).toString() + "\n")
        }
    }

    private fun sendCommand(input: String) {
        if (m_bluetoothSocket != null) {
            try{
                m_bluetoothSocket!!.outputStream.write(input.toByteArray())
            } catch(e: IOException) {
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
        finish()
    }

    private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String>() {
        private var connectSuccess: Boolean = true
        private val context: Context

        init {
            this.context = c
        }

        override fun onPreExecute() {
            super.onPreExecute()
            m_progress = ProgressDialog.show(context, "Connecting...", "please wait")
        }

        override fun doInBackground(vararg p0: Void?): String? {
            try {
                if (m_bluetoothSocket == null || !m_isConnected) {
                    m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(m_address)
                    m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    m_bluetoothSocket!!.connect()
                }
            } catch (e: IOException) {
                connectSuccess = false
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!connectSuccess) {
                Log.i("data", "couldn't connect")
            } else {
                m_isConnected = true
            }
            m_progress.dismiss()
        }
    }
}