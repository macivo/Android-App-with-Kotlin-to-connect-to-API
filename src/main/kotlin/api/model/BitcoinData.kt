package api.model
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.*
import java.net.URL

/**
 * Model Class: get the data via API
 * The data will be parsed with jacksonObjectMapper to Bitcoin.kt
 */
class BitcoinData {
    private val url = "https://api.coindesk.com/v1/bpi/currentprice.json"
    private lateinit var bitcoinData: Bitcoin

    private fun getBitcoinData(): Unit {
        val json = URL(url)
        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        bitcoinData = mapper.readValue(json)
    }
    /**
     * Call API with ExceptionHandler
     * Time out after 3 seconds
     * Use Dispatchers.IO is the best way to receiving a stream data
     */
    private fun start(): Unit {
        val handler = CoroutineExceptionHandler { _, ex ->
            println("Exception: ${ex.message}")
        }
        runBlocking {
            val bitcoinData = launch(Dispatchers.IO + handler) {
                withTimeout(3000) {
                    supervisorScope {
                        launch { fetchResponse()}
                    }
                }
            }
        }
    }
    /** Suspend Function for coroutine */
    private suspend fun fetchResponse() = coroutineScope {
        try {
            async { getBitcoinData() }.await()
            println(" ${Thread.currentThread()} :: $bitcoinData.toString() ")
        } catch (ex: CancellationException) {
            println("${ex.message}")
        }
    }

    /**
     * Only a public to call the model
     * @return Bitcoin object
     * */
    fun getData(): Bitcoin {
        start()
        return bitcoinData
    }
}