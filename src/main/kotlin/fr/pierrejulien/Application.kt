package fr.pierrejulien

import fr.pierrejulien.db.DatabaseFactory
import fr.pierrejulien.repository.UserRepository
import fr.pierrejulien.repository.UserRepositoryImpl
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import fr.pierrejulien.routes.authRoutes
import fr.pierrejulien.service.UserService
import fr.pierrejulien.service.UserServiceImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        install(ContentNegotiation) { jackson() }

        val userService: UserService = UserServiceImpl()
        val userRepository: UserRepository = UserRepositoryImpl(userService)

        authRoutes(userRepository)
    }.start(wait = true)
}
