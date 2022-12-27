package org.dto

class SimpleMovieDTO(val id : String, val title : String, val description : String, val poster : String,
                     val categories : MutableList<CategoryDTO>, val score : Int)
