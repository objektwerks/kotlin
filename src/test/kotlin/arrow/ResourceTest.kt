package arrow

import arrow.fx.coroutines.resource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import kotlin.test.Test

class ResourceTest {
    class DataSource {
        fun connect(): Unit = println("Connecting to datasource...")
        fun close(): Unit = println("Closed datasource connection.")
        fun query(): List<String> = listOf("a", "b", "c")
    }

    @Test
    fun resource() {
        val dataSourceAsResource = resource( {
            DataSource().also { it.connect() }
        } ) { ds, exitCase ->
            println("Releasing $ds with exit: $exitCase")
            withContext(Dispatchers.IO) { ds.close() }
        }
        val result = runBlocking {
            dataSourceAsResource.use { ds -> ds.query() }
        }
        assert( result.size == 3 )
    }
}