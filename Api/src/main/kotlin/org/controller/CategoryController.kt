package org.controller

import com.github.unqUi.model.RottenTomatoesSystem
import io.javalin.http.Context
import org.dto.ErrorDTO
import org.dto.ResultCategoriesDTO
import org.dto.ResultSimpleMoviesDTO

class CategoryController(private val rottenTomatoes: RottenTomatoesSystem) : Controller() {
    fun getAllCategories(ctx: Context) {
        ctx.json(ResultCategoriesDTO(convertToListOfCategoriesDTO(rottenTomatoes.categories)))
    }
    fun getCategoryByID(ctx: Context) {
        try {
            val category = rottenTomatoes.getCategoryById(ctx.pathParam("id"))
            val moviesByCategory = getAllMoviesByCategory(rottenTomatoes.movies, category)
            ctx.json(ResultSimpleMoviesDTO(convertToListSimpleMovieDTO(moviesByCategory, rottenTomatoes.reviews)))
        } catch (exc: NoSuchElementException) {
            ctx.json(ErrorDTO("The requested category does not exist in the system")).status(404)
        }
    }
}