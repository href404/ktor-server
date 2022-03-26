package fr.pierrejulien.service

import fr.pierrejulien.db.DatabaseFactory.dbQuery
import fr.pierrejulien.db.UserTable
import fr.pierrejulien.models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = params.password // Todo: Encrypt password
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }

        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        return dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { rowToUser(it) }.singleOrNull()
        }
    }

    private fun rowToUser(row: ResultRow?): User? {
        if (row == null)
            return null

        return User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            avatar = row[UserTable.avatar],
            email = row[UserTable.email],
            createdAt = row[UserTable.createdAt].toString(),
            updatedAt = row[UserTable.updatedAt].toString()
        )
    }
}