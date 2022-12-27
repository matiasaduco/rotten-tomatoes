package org.controller
import com.github.unqUi.model.*
import io.javalin.http.Context
import org.dto.ReviewDTO
import org.dto.SimpleReviewDTO

class ReviewController(private val rottenTomatoes: RottenTomatoesSystem) : Controller() {

    fun addReview(ctx: Context) {
        validationJSONBody(ctx, this::validateReviewDTO)
    }
    private fun validateReviewDTO(ctx: Context) {
        ctx.status(404)
        val movie = rottenTomatoes.getMovieById(ctx.pathParam("id"))
        val user = ctx.attribute<User>("user")!!
        val reviewToRegister = ctx.bodyValidator<ReviewDTO>()
            .check({ it.text.isNotBlank()}, "Text cannot be blank")
            .check({ it.stars in 0..5 }, "The number of stars entered is not valid. Only values between 0 and 5 inclusive are allowed.")
            .get()
        val review = rottenTomatoes.addReview(DraftReview(user.id, movie.id, reviewToRegister.text, reviewToRegister.stars))
        ctx.status(200)
        ctx.json(SimpleReviewDTO(review.id, userToUserDTO(user), convertToSimpleMovieDTO(movie, rottenTomatoes.reviews), review.text, review.stars))
    }
}