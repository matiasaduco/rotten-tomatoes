package org

import com.github.unqUi.model.RottenTomatoesSystem
import com.github.unqUi.model.getRottenTomatoesSystem
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.controller.*
import org.token.Roles
import org.token.TokenAccessManager
import org.token.TokenJWT

fun main() {
    val tokenJWT = TokenJWT()
    val rottenTomatoes : RottenTomatoesSystem = getRottenTomatoesSystem()
    val authController = AuthController(rottenTomatoes, tokenJWT)
    val userController = UserController(rottenTomatoes)
    val reviewController = ReviewController(rottenTomatoes)
    val contentController = ContentController(rottenTomatoes)
    val categoryController = CategoryController(rottenTomatoes)

    val app = Javalin.create{
        it.accessManager(TokenAccessManager(tokenJWT, rottenTomatoes))
        it.enableCorsForAllOrigins()
    }.start(7080)

    app.before {
        it.header("Access-Control-Expose-Headers", "*")
    }

    app.routes {
        path("login") {
            post(authController::login)
        }
        path("register") {
            post(authController::register)
        }

        path("user") {
            get(userController::getUserByHeader, Roles.USER)
            path("{id}") {
                get(userController::getUserByIdParameter)
            }
        }
        path("content") {
            path("latest") {
                get(contentController::getLatestContent)
            }
            path("top") {
                get(contentController::getTopContent)
            }
            path("{id}") {
                post(reviewController::addReview, Roles.USER)
                get(contentController::getMovieByID)
            }
        }
        path("categories") {
            get(categoryController::getAllCategories)
            path("{id}") {
                get(categoryController::getCategoryByID)
            }
        }
    }
}
