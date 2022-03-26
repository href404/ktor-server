package fr.pierrejulien.repository

import fr.pierrejulien.service.CreateUserParams
import fr.pierrejulien.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}