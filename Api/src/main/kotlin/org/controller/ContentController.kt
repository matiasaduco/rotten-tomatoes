package org.controller

import com.github.unqUi.model.*
import io.javalin.http.Context
import org.dto.*

class ContentController(private val rottenTomatoes: RottenTomatoesSystem) : Controller() {
    fun getMovieByID(ctx: Context) {
        lateinit var movieByID : Movie
        try {
            movieByID = rottenTomatoes.getMovieById(ctx.pathParam("id"))
        } catch (e: MovieError) {
            ctx.json(ErrorDTO("The requested movie does not exist in the system")).status(404)
            return
        }
        val categoriesDTO = convertToListOfCategoriesDTO(movieByID.categories)
        val relatedContentDTO = convertToListOfSimpleMovieDTO(movieByID.relatedContent, rottenTomatoes.reviews)
        val reviewsDTO = filterAndConvertToListOfSimpleReviewDTO(rottenTomatoes.reviews, movieByID.id)
        val score = getAverageScore(rottenTomatoes.reviews, movieByID)
        val movieDTO = MovieDTO(movieByID.id, movieByID.title, movieByID.description, movieByID.poster, categoriesDTO, relatedContentDTO, score, reviewsDTO)
        ctx.json(movieDTO)
    }
    fun getLatestContent(ctx: Context) {
        // Se da por entendido en swagger que existen al menos 10 peliculas en el sistema.
        // * Toma las últimas 10 películas de la lista, las convierte en DTO y las devuelve al context
        // * Se aplica el reverse para que la primer película en aparecer sea la más reciente en ser agregada
        val latestMovies = rottenTomatoes.movies.takeLast(10)
        val latestContent: MutableList<SimpleMovieDTO> = mutableListOf()
        latestMovies.forEach { latestContent.add(convertToSimpleMovieDTO(it, rottenTomatoes.reviews)) }
        latestContent.reverse()
        ctx.json(ResultSimpleMoviesDTO(latestContent.toMutableList()))
    }
    fun getTopContent(ctx: Context) {
        /* Toma la lista de películas, las transforma en una lista de MovieDTO
         * Ya que ahí ya está el dato del score de cada una
         * Se ordena por score, se aplica reverse ya que SortBy ordena de forma ascendente */
        val moviesDTO = convertToListOfSimpleMovieDTO(rottenTomatoes.movies, rottenTomatoes.reviews)
        moviesDTO.sortBy { movie -> movie.score }
        moviesDTO.reverse()
        ctx.json(ResultSimpleMoviesDTO(moviesDTO.take(10).toMutableList()))
    }
}