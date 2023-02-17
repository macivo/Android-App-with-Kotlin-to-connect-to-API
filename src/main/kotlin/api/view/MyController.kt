package api.view
import api.model.Bitcoin
import api.model.BitcoinData
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import org.jsoup.Jsoup
import tornadofx.Controller
import tornadofx.asObservable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/** Enum for 3 currencies */
enum class Currency {USD, GBP, EUR}

class MyController: Controller() {
    /**
     * The controller of mainView
     */
    var data = BitcoinData().getData()
    val time = SimpleStringProperty()
    val price = SimpleStringProperty()
    val currencySymbol = SimpleStringProperty()
    var currency = Currency.USD
    var description = SimpleStringProperty()
    var datas = mutableListOf<Bitcoin>().asObservable()

    /**
     * Scheduling call to update the prices every minute
     */
    init {
        val exc = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate({
            data = BitcoinData().getData()
            Platform.runLater {
                time.set(data.time.updated)
                setControlCurrency()
                datas.add(0, data)
            }
        }, 0, 1, TimeUnit.MINUTES)
    }

    /**
     * User can select one of three currencies to show.
     * For the histories. All 3 currencies will be showed as the same times in the list
     */
    public fun setControlCurrency(): Unit {
        var info = data.bpi.usd
        when (currency) {
            Currency.GBP -> info = data.bpi.gbp
            Currency.EUR -> info = data.bpi.eur
        }
        price.set(String.format("%.2f", info.rate_float))
        description.set(info.description)
        currencySymbol.set(" "+ Jsoup.parse(info.symbol).text())
    }
}