package fr.pierrejulien.repository

import fr.pierrejulien.models.User
import fr.pierrejulien.service.CreateUserParams
import fr.pierrejulien.service.UserService
import fr.pierrejulien.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        if (isEmailExist(params.email))
            return BaseResponse.ErrorResponse(message = "Email already registered")

        val user = userService.registerUser(params) ?: return BaseResponse.ErrorResponse()

        // Todo; Generate authentification token for the user
        return BaseResponse.SuccessResponse(user)
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}