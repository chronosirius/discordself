package xyz.chronosirius.discordself

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlin.collections.Map
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

class GatewayConnection {
    public val token: String;
    public val client: HttpClient;
    public var handler: EventHandler? = null;
        private set;
    public var session: DefaultClientWebSocketSession? = null;
        private set;

    constructor (token: String, client: HttpClient) {
        this.token = token;
        this.client = client;
    }

    suspend fun connect(handler: EventHandler): Boolean {
        // Connect, maintain, dispatch - NO SENDING IS REQUIRED BEYOND IDENTIFY + HEARTBEAT
        client.webSocket(
            method = HttpMethod.Get,
            host = "gateway.discord.gg",
            port = 443,
            path = "/?v=9&encoding=json"
        ) {
            while(true) {
                val frame = incoming.receive() as Frame.Text;
                val payload = Json.parseToJsonElement(frame.readText());
                val heartbeat_interval = payload.jsonObject["d"]?.jsonObject?.get("heartbeat_interval")?.jsonObject.toString().toLong();
                var last_beat = System.currentTimeMillis();
                sendSerialized(mapOf(
                    "op" to 2,
                    "d" to mapOf(
                        "token" to token,
                        "properties" to mapOf(
                            "os" to "Linux",
                            "browser" to "idk",
                            "device" to "idk"
                        )
                    ),
                    "s" to null,
                    "t" to null
                ))
                var seq = null;
                while (true) {
                    if (System.currentTimeMillis() - last_beat > heartbeat_interval) {
                        last_beat = System.currentTimeMillis();
                        sendSerialized(mapOf(
                            "op" to 1,
                            "d" to seq
                        ))
                    }

                }
            }
        }
        return true;
    }
}