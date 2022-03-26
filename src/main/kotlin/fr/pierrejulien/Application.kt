package fr.pierrejulien

import fr.pierrejulien.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import fr.pierrejulien.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        configureRouting()
    }.start(wait = true)
}
