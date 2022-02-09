package banana.duo.duoKotlin

import android.R.attr
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import banana.duo.Common.ActionType
import banana.duo.Common.Message
import banana.duo.duoKotlin.Client.currentClient
import banana.duo.duoKotlin.databinding.ActivityMouseBinding
import io.github.controlwear.virtual.joystick.android.JoystickView
import android.R.attr.button
import android.view.View.OnTouchListener


class MouseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMouseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMouseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setListeners() {
        binding.mouseJoystick.setOnMoveListener { angle, strength ->
            val x = (Math.cos(Math.toRadians(angle.toDouble())) * strength).toInt() / 10
            val y = (-(Math.sin(Math.toRadians(angle.toDouble())) * strength)).toInt() / 10
            val message = Message(
                ActionType.MouseMove,
                mapOf(Pair("x", x.toString()), Pair("y", y.toString()))
            )
            currentClient?.sendMessage(message)
        }
        binding.rightButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentClient?.sendMessage(Message(ActionType.MouseClick, mapOf(Pair("type", "rightDown"))))
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    currentClient?.sendMessage(Message(ActionType.MouseClick, mapOf(Pair("type", "rightUp"))))
                }
            }
            false
        })
        binding.leftButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentClient?.sendMessage(Message(ActionType.MouseClick, mapOf(Pair("type", "leftDown"))))
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    currentClient?.sendMessage(Message(ActionType.MouseClick, mapOf(Pair("type", "leftUp"))))
                }
            }
            false
        })
    }

}