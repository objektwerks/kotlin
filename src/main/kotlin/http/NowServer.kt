package http

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import java.time.Instant

fun main() {
    val server = embeddedServer(Netty, port = 7979) {
        routing {
            get("/now") {
                call.respondText("Ktor datetime is: ${Instant.now()}")
            }
        }
    }
    println("*** Server started on localhost:7979")
    println("*** See log at build/kotlin.log")
    println("*** Select: http://localhost:7979/now")
    server.start(wait = true)
}