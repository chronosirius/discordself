package xyz.chronosirius.discordself

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RESTHandler {
    public val token: String;
    public val client: HttpClient;

    constructor (token: String, client: HttpClient) {
        this.token = token;
        this.client = client;
    }

    suspend fun getGatewayURL(): String {
        client.get<String>("gateway") {
        }
    }
}