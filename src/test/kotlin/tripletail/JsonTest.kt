package tripletail

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import org.junit.Test

@Serializable data class Car(val type: String, val model: String)

class JsonTest {
    @Test fun json() {
        val porsche = Car("Porsche", "911")
        val json = Json.encodeToString(porsche)
        assert( Json.decodeFromString<Car>(json) == porsche )
    }
}