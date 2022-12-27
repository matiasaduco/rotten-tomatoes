package org.dto

class MovieDTO(val id : String, val title : String, val description : String, val poster : String,
               val categories : MutableList<CategoryDTO>, val relatedContent : MutableList<SimpleMovieDTO>,
               val score : Int, val reviews : MutableList<SimpleReviewDTO>)