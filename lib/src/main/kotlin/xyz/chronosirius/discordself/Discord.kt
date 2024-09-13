package xyz.chronosirius.discordself

import xyz.chronosirius.discordself.GatewayConnection
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

class Discord {
    private val client: HttpClient; // This is nullable because it is initialized in the  function
    private val token: String;
    private var gateway: GatewayConnection? = null;

    constructor (token: String, ua: String? = null) {
        this.token = token;
        this.client = HttpClient(OkHttp) {
            install(WebSockets) {
                maxFrameSize = 4096
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }
            install(UserAgent) {
                agent = ua ?: "DiscordSelf" // For Accordion, this will be Discord-Android/[version without dots];Accordion
            }
            defaultRequest {
                headers.append("Authorization", token)
                url("https://discord.com/api/v9/")
            }
            expectSuccess = false
            engine {
                config {
                    followRedirects(true)
                }
            }

        }
    }

    suspend fun initialize() {
        
    }
}