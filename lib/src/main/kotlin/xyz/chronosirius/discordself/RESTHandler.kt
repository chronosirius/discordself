package xyz.chronosirius.discordself

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.Serializable

class RESTHandler {
    public val token: String;
    public val client: HttpClient;

    constructor (token: String, client: HttpClient) {
        this.token = token;
        this.client = client;
    }

    suspend fun getGatewayURL(): String {
        val response = client.get("gateway")
        log(response.bodyAsText())

        val jsonResponse = Json.parseToJsonElement(response.bodyAsText()) as JsonObject
        return jsonResponse.get("url").toString()
    }

    suspend fun post(endpoint: String, body: JsonElement) {
        val jsonBody = Json.encodeToString(JsonElement.serializer(), body)
        client.post(endpoint) {
            setBody(jsonBody)
        }
    }

    suspend fun get(endpoint: String): JsonElement {
        val response = client.get(endpoint)
        return Json.parseToJsonElement(response.bodyAsText())
    }
}