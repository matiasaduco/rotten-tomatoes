package org.controller
import com.github.unqUi.model.RottenTomatoesSystem
import com.github.unqUi.model.User
import com.github.unqUi.model.UserError
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.dto.ErrorDTO
import org.dto.UserWithReviewsDTO

class UserController(private val rottenTomatoes: RottenTomatoesSystem) : Controller() {

    fun getUserByHeader(ctx : Context) {
        try {
            val user = ctx.attribute<User>("user")!!
            ctx.json(UserWithReviewsDTO(user.id, user.name, user.image, user.email, convertToListOfSimpleReviewDTO(user.reviews))).status(200)
        } catch (exc: UnauthorizedResponse) {
            ctx.json(ErrorDTO(exc.message!!)).status(401)
        }
    }

    fun getUserByIdParameter(ctx : Context) {
        try {
            val user = rottenTomatoes.getUserById(ctx.pathParam("id"))
            ctx.json(UserWithReviewsDTO(user.id, user.name, user.image, user.email, convertToListOfSimpleReviewDTO(user.reviews))).status(200)
        } catch (exc: UserError) {
            ctx.status(404)
            ctx.json(ErrorDTO(exc.message!!))
        }
    }
}