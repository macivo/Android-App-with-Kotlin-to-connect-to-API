package api.model
import com.fasterxml.jackson.annotation.JsonProperty
/**
 * Bitcoin data class
 */

data class Bitcoin(
    @JsonProperty("bpi") val bpi: BPI,
    @JsonProperty("time") val time: Time
)

data class Time(
    @JsonProperty("updated") val updated: String
)
data class BPI (
    @JsonProperty("USD") val usd: Info,
    @JsonProperty("GBP") val gbp: Info,
    @JsonProperty("EUR") val eur: Info
)

data class Info (
    @JsonProperty("code") val code: String,
    @JsonProperty("symbol") val symbol: String,
    @JsonProperty("rate") val rate: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("rate_float") val rate_float: Double
)