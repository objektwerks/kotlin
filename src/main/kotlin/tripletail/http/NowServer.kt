package tripletail.http

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import java.time.Instant

/**
 * To test server: curl http://localhost:7979/now
 */
fun main() {
    embeddedServer(Netty, port = 7979) {
        routing {
            get("/now") {
                call.respondText("Ktor datetime is: ${Instant.now()}")
            }
        }
    }.start(wait = true)
}