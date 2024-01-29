package arrow

import arrow.fx.coroutines.resource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import kotlin.test.Test

class ResourceTest {
    class DataSource {
        fun connect(): Unit = println("*** Connecting to data source...")
        fun close(): Unit = println("*** Closed data source connection.")
        fun query(): List<String> = listOf("a", "b", "c")
    }

    @Test
    fun resource() {
        val dataSourceAsResource = resource( {
            DataSource().also { it.connect() }
        } ) { ds, exitCase ->
            println("*** Releasing data source with exit case: $exitCase")
            withContext(Dispatchers.IO) { ds.close() }
        }
        val result = runBlocking {
            dataSourceAsResource.use { ds -> ds.query() }
        }
        println("*** Data source query: $result.")
        assert( result.size == 3 )
    }
}