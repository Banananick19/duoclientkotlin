package banana.duo.duoKotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import banana.duo.Common.ActionType
import banana.duo.Common.Message
import banana.duo.duoKotlin.Client.currentClient
import io.github.controlwear.virtual.joystick.android.JoystickView


class MouseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mouse)
        setListeners()
    }

    fun setListeners() {
        findViewById<JoystickView>(R.id.mouse_joystick).setOnMoveListener { angle, strength ->
            val x = (Math.cos(Math.toRadians(angle.toDouble())) * strength).toInt() / 10
            val y = (-(Math.sin(Math.toRadians(angle.toDouble())) * strength)).toInt() / 10
            val message = Message(
                ActionType.MouseMove,
                mapOf(Pair("x", x.toString()), Pair("y", y.toString()))
            )
            currentClient?.sendMessage(message)

        }
    }

}