package banana.duo.duoKotlin.Client

import banana.duo.Common.Message

abstract class Client {
    abstract fun sendMessage(message: Message)
    abstract fun connect(): Boolean
}