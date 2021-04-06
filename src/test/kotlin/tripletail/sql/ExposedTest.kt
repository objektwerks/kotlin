package tripletail.sql

import com.sksamuel.hoplite.ConfigLoader
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import org.junit.Test

data class H2Config(val url: String, val driver: String, val user: String, val password: String)

object Todos : Table() {
    val id = integer("id").autoIncrement()
    val task = varchar("task", 128)
    override val primaryKey = PrimaryKey(id, name = "pk")
}

class ExposedTest {
    @Test fun exposed() {
        val config = ConfigLoader().loadConfigOrThrow<H2Config>("/exposed.yaml")
        Database.connect(
            url = config.url,
            driver = config.driver,
            user = config.user,
            password = config.password
        )
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create( Todos )

            val todosId = Todos.insert {
                it[task] = "Wash the car."
            } get Todos.id
            assert( todosId > 0 )

            assert(
                Todos.select { Todos.id eq todosId }.single()[Todos.task] == "Wash the car."
            )

            Todos.update( { Todos.id eq todosId } ) {
                it[task] = "Wash the car and drink beer!"
            }

            assert(
                Todos.select { Todos.id eq todosId }.single()[Todos.task] == "Wash the car and drink beer!"
            )

            Todos.deleteWhere{ Todos.id eq todosId }

            assert( Todos.selectAll().count() == 0L )

            SchemaUtils.drop( Todos )
        }
    }
}