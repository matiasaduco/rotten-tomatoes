package org.dto

class UserWithReviewsDTO(val id : String, val name : String, val image : String, val email : String, val reviews : MutableList<SimpleReviewDTO>)