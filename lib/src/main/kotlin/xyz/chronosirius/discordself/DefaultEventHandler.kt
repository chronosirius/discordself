package xyz.chronosirius.discordself

import xyz.chronosirius.discordself.models.*
import kotlinx.serialization.json.JsonElement

interface EventHandler {
    /*
    * All event handlers must implement this interface. The EventHandler will be passed to the GatewayConnection.connect method.
    * The methods will be called when their respective events are received.
    * The methods will be given all data receieved from the gateway as a model class (in xyz.chronosirius.discordself.models) or, if no model exists, as a JsonElement.
     */
    abstract fun onReady(d: JsonElement)
    abstract fun onMessageCreate(d: JsonElement)
    abstract fun onMessageUpdate(d: JsonElement)
    abstract fun onMessageDelete(d: JsonElement)
}