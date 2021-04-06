package tripletail

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

import java.time.Instant

object KtorApp {
    @JvmStatic fun main(args : Array<String>) {
        embeddedServer(Netty, port = 7979) {
            routing {
                get("/") {
                    call.respondText("Ktor datetime is: ${Instant.now()}")
                }
            }
        }.start(wait = true)
    }
}