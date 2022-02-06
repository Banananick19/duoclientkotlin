package banana.duo.duoKotlin.Client

import banana.duo.Common.ActionType
import banana.duo.Common.Message
import java.io.*
import java.net.Socket

class ClientWiFi private constructor(var ip: String, var port: Int) : Client() {
    var clientSocket: Socket? = null
    var `in`: BufferedReader? = null
    var out: BufferedWriter? = null
    fun hasConnection(): Boolean {
        return clientSocket != null
    }

    override fun connect(): Boolean {
        try {
            instance!!.clientSocket = Socket(ip, port)
            instance!!.`in` = BufferedReader(
                InputStreamReader(
                    clientSocket!!.getInputStream()
                )
            )
            instance!!.out = BufferedWriter(
                OutputStreamWriter(
                    clientSocket!!.getOutputStream()
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
        println("Connected $ip:$port")
        return true
    }

    @Throws(IOException::class)
    override fun sendMessage(message: Message) {
        out!!.write(
            """
                $message
                
                """.trimIndent()
        ) // отправляем сообщение на сервер
        out!!.flush()
    }

    @Throws(IOException::class)
    fun getMessage(actionType: ActionType?): Message {
        return Message.parseString(`in`!!.readLine())
    }

    companion object {
        var instance: ClientWiFi? = null

        fun createInstance(ip: String, port: Int) {
            instance = ClientWiFi(ip, port)
        }

        fun hasInstance(): Boolean {
            return instance != null
        }
    }
}