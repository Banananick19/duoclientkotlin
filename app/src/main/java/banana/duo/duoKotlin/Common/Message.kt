package banana.duo.Common

import com.google.gson.Gson
import banana.duo.Common.ActionType

class Message(actionType: ActionType, content: Map<String, String>) {
    private val actionType: ActionType
    val content: Map<String, String>

    override fun toString(): String {
        return gson.toJson(this)
    }

    val messageType: ActionType
        get() = actionType

    companion object {
        var gson: Gson = Gson()
        fun parseString(string: String?): Message {
            return gson.fromJson(string, Message::class.java)
        }
    }

    init {
        this.actionType = actionType
        this.content = content
    }
}