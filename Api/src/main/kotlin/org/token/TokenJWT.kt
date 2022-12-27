package org.token
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.unqUi.model.User
import io.javalin.http.UnauthorizedResponse
import javalinjwt.JWTProvider

class TokenJWT {
    private val algorithm = Algorithm.HMAC256("secret")
    private val generator = UserGenerator()
    private val verifier = JWT.require(algorithm).build()
    private val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validate(thisToken: String): String {
        val token = provider.validateToken(thisToken)
        if (!token.isPresent) throw UnauthorizedResponse("Token is not valid")
        return token.get().getClaim("id").asString()
    }
}