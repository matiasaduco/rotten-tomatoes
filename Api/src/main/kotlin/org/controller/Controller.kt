package org.controller
import com.github.unqUi.model.*
import io.javalin.core.validation.ValidationException
import io.javalin.http.Context
import org.dto.*

abstract class Controller {

    fun validationJSONBody(ctx : Context, function: (Context) -> Unit) {
        try {
            function(ctx)
        } catch (exc: MovieError) {
            ctx.json(ErrorDTO(exc.message!!))
        } catch (c: ValidationException) {
            ctx.json(ErrorDTO(c.errors["REQUEST_BODY"]!!.first().message))
        } catch (exc: ReviewError) {
            ctx.json(ErrorDTO(exc.message!!))
        }
    }

    fun userToUserDTO(user: User): UserDTO {
        // Devuelve un UserDTO
        return UserDTO(user.id, user.name, user.image, user.email)
    }

    fun getAllMoviesByCategory(movies: MutableList<Movie>, category: Category): MutableList<Movie> {
        return movies.filter { movie ->  movie.categories.contains(category)}.toMutableList()
    }

    fun convertToListSimpleMovieDTO(movies: MutableList<Movie>, reviews: MutableList<Review>): MutableList<SimpleMovieDTO> {
        val simpleMoviesDTOList = mutableListOf<SimpleMovieDTO>()
        movies.forEach { movie -> simpleMoviesDTOList.add(convertToSimpleMovieDTO(movie, reviews))}
        return simpleMoviesDTOList
    }

    fun convertToSimpleMovieDTO(movie: Movie, reviews: MutableList<Review>): SimpleMovieDTO {
        // Convierte un objeto Movie en MovieDTO
        // Requiere la lista de Reviews para poder generar su puntuación promedio
        val categoriesDTO = convertToListOfCategoriesDTO(movie.categories)
        val score = getAverageScore(reviews, movie)
        return SimpleMovieDTO(movie.id, movie.title, movie.description, movie.poster, categoriesDTO, score)
    }
    fun convertToListOfSimpleReviewDTO(reviews: MutableList<Review>) : MutableList<SimpleReviewDTO> {
        /*
        * Convierte las Reviews en una lista de ReviewsDTO
        * Hay que pasar el sistema por parametro a convertReviewToSimpleReviewDTO
        * Porque cuando se crea la reviewDTO se necesita una MovieDTO y esta a su vez necesita su score
        * El cual es obtenido de la lista de Reviews ( esta lista no es necesario que sea del tipo DTO
        */
        val listOfSimpleReviewsDTO = mutableListOf<SimpleReviewDTO>()
        reviews.forEach { listOfSimpleReviewsDTO.add(convertReviewToSimpleReviewDTO(it, reviews)) }
        return listOfSimpleReviewsDTO
    }

    fun filterAndConvertToListOfSimpleReviewDTO(reviews: MutableList<Review>, movieID: String): MutableList<SimpleReviewDTO> {
        /*
        * Convierte las Reviews en una lista de ReviewsDTO
        * Hay que pasar el sistema por parametro a convertReviewToSimpleReviewDTO
        * Porque cuando se crea la reviewDTO se necesita una MovieDTO y esta a su vez necesita su score
        * El cual es obtenido de la lista de Reviews ( esta lista no es necesario que sea del tipo DTO
        */
        val reviewsByMovie = mutableListOf<SimpleReviewDTO>()
        reviews.forEach { if(it.movie.id == movieID) reviewsByMovie.add(convertReviewToSimpleReviewDTO(it, reviews)) }
        return reviewsByMovie
    }





    private fun convertReviewToSimpleReviewDTO(review: Review, reviews: MutableList<Review>): SimpleReviewDTO {
        return SimpleReviewDTO(review.id, userToUserDTO(review.user), convertToSimpleMovieDTO(
            review.movie, reviews), review.text, review.stars
        )
    }
    fun convertToListOfCategoriesDTO(categories: MutableList<Category>): MutableList<CategoryDTO> {
        val categoriesDTO: MutableList<CategoryDTO> = mutableListOf()
        categories.forEach { category -> categoriesDTO.add(convertToCategoryDTO(category)) }
        return categoriesDTO
    }
    private fun convertToCategoryDTO(category: Category): CategoryDTO {
        return CategoryDTO(category.id, category.name)
    }

    fun convertToListOfSimpleMovieDTO(relatedContent: MutableList<Movie>, reviews: MutableList<Review>): MutableList<SimpleMovieDTO> {
        // Convierte cada elemento de la lista en un DTO.
        val simpleMoviesDTO: MutableList<SimpleMovieDTO> = mutableListOf()
        relatedContent.forEach { movie -> simpleMoviesDTO.add(convertToSimpleMovieDTO(movie, reviews)) }
        return simpleMoviesDTO
    }
    fun getAverageScore(reviewsDTO: MutableList<Review>, movieByID: Movie): Int {
        // Devuelve la puntuación promedio de una película pasada por parámetro.
        // Se requiere chequear que la película tenga reviews, para evitar que divida por 0
        var score = 0
        val movieReviews = reviewsDTO.filter { it.movie.title == movieByID.title }
        movieReviews.forEach { score += it.stars }
        if(movieReviews.isNotEmpty()) { score /= movieReviews.size }
        return score
    }
}