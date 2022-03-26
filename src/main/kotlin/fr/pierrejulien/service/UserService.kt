package fr.pierrejulien.service

import fr.pierrejulien.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
}