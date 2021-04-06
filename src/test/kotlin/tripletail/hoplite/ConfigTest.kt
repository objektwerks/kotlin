package tripletail.hoplite

import com.sksamuel.hoplite.ConfigLoader

import org.junit.Test

data class Config(val host: String, val port: Int)

class ConfigTest {
    @Test fun config() {
        val config = ConfigLoader().loadConfigOrThrow<Config>("/config.yaml")
        assert(config.host == "localhost")
        assert(config.port == 7979)
    }
}