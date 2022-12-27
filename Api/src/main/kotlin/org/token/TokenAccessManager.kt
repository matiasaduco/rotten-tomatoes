package org.token
import com.github.unqUi.model.RottenTomatoesSystem
import io.javalin.core.security.AccessManager
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.dto.ErrorDTO

class TokenAccessManager(private val tokenJWT: TokenJWT, private val rottenTomatoes: RottenTomatoesSystem) : AccessManager {

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<RouteRole>) {
        val token = ctx.header("Authorization")
        when {
            roles.isEmpty() -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Roles.USER) -> {
                try {
                    val userId = tokenJWT.validate(token)
                    ctx.attribute("user", rottenTomatoes.getUserById(userId))
                    handler.handle(ctx)
                } catch (e: UnauthorizedResponse) {
                    ctx.json(ErrorDTO(e.message!!)).status(401)
                }
            }
        }
    }
}
