package fr.pierrejulien.security

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity() {

    JwtConfig.initialize("my-story-app")

    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if (claim != null) UserIdPrincipalForUser(claim) else null
            }
        }
    }
}