package xyz.chronosirius.discordself.models

class Message {
    val id: String
    val content: String
    val author: User
    val timestamp: String

    constructor(id: String, content: String, author: User, timestamp: String) {
        this.id = id
        this.content = content
        this.author = author
        this.timestamp = timestamp
    }
}