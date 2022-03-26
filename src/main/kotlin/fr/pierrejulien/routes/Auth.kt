package fr.pierrejulien.routes

import fr.pierrejulien.repository.UserRepository
import fr.pierrejulien.service.CreateUserParams
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoutes(repository: UserRepository) {
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}