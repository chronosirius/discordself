package xyz.chronosirius.discordself

import io.ktor.client.HttpClient

class GatewayConnection {
    public val token: String;
    public val client: HttpClient;

    constructor (token: String, client: HttpClient) {
        this.token = token;
        this.client = client;
    }

    fun connect(handler: Any?): GatewayConnection {
        return this
    }
}