package org.controller

import com.github.unqUi.model.DraftUser
import com.github.unqUi.model.RottenTomatoesSystem
import io.javalin.http.Context
import org.dto.UserDTO
import org.dto.UserLoginDTO
import org.dto.UserRegisterDTO
import org.token.TokenJWT

class AuthController(private val rottenTomatoes: RottenTomatoesSystem, private val tokenJWT: TokenJWT) : Controller() {

    fun login(ctx : Context) {
        validationJSONBody(ctx, this::validateUserLoginDTO)
    }

    fun register(ctx : Context) {
        validationJSONBody(ctx, this::validateUserRegisterDTO)
    }

    private fun validateUserRegisterDTO(ctx : Context) {
        ctx.status(400)
        val userToRegister = ctx.bodyValidator<UserRegisterDTO>()
            .check({ it.email.isNotBlank()}, "Email cannot be blank")
            .check({ it.email.contains("@")}, "Email does not contain the @ symbol")
            .check({ !rottenTomatoes.users.any { user -> user.email == it.email }}, "Email is all ready registered")
            .check({ it.password.isNotBlank()}, "Password cannot be blank")
            .check({ it.name.isNotBlank()}, "Name cannot be blank")
            .check({ it.image.contains("jpg") || it.image.contains("png") || it.image.contains("bmp")}, "Image does not respect a valid format")
            .get()
        ctx.status(200)
        val newUser = rottenTomatoes.addUser(DraftUser(userToRegister.name, userToRegister.image, userToRegister.email, userToRegister.password))
        ctx.header("Authorization", tokenJWT.generateToken(newUser))
        ctx.json(UserDTO(newUser.id, newUser.name, newUser.image, newUser.email))
    }
    private fun validateUserLoginDTO(ctx : Context) {
        ctx.status(400)
        val userToLogin = ctx.bodyValidator<UserLoginDTO>()
            .check({ it.email.isNotBlank()}, "Email cannot be empty")
            .check({ it.email.contains("@")}, "Email does not contain the @ symbol")
            .check({ it.password.isNotBlank()}, "Password cannot be blank")
            .get()
        ctx.status(404)
        ctx.bodyValidator<UserLoginDTO>()
            .check({ rottenTomatoes.users.any { user -> user.email == it.email }}, "Email is not registered")
            .check({ rottenTomatoes.users.any { user -> user.email == it.email && user.password == it.password }}, "Password is not valid")
            .get()
        ctx.status(200)
        val user = rottenTomatoes.users.first { user -> user.email == userToLogin.email }
        ctx.header("Authorization", tokenJWT.generateToken(user))
        ctx.json(userToUserDTO(user))
    }

}