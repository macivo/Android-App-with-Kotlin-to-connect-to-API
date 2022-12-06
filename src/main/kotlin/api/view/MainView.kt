package api.view

import api.app.Styles
import api.model.Bitcoin
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import tornadofx.*

/**
 * View design for the application
 */
class MainView : View("BitcoinApp::mullk8") {
    private val controller: MyController by inject()

    /**
     * First Tab: shows the current bitcoin price
     * User can select which price to show
     */
    override val root = tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("Price") {
            separator()
            vbox() {
                style {
                    alignment = Pos.TOP_CENTER
                }
                addClass(Styles.main)
                vbox() {
                    style {
                        alignment = Pos.CENTER
                        setMinHeight(250.0)
                    }
                    addClass(Styles.heading)
                    label("Bitcoin Price")

                    label(controller.time){
                        textProperty().bind(controller.time)
                    }
                    label(controller.description) {
                        textProperty().bind(controller.description)
                    }
                    hbox {
                        style {
                            alignment = Pos.CENTER
                            setMinHeight(250.0)
                        }
                        addClass(Styles.price)
                        label(controller.price){
                            textProperty().bind(controller.price)
                        }
                        label(controller.currencySymbol){
                            textProperty().bind(controller.currencySymbol)
                        }
                    }

                }
                separator()
                hbox(alignment = Pos.BOTTOM_CENTER) {
                    togglegroup {
                        val dollar = radiobutton("US Dollar", this) {
                            action {
                                controller.currency = Currency.USD
                                controller.setControlCurrency()
                            }
                            hboxConstraints {
                                padding = Insets(10.0, 5.0, 0.0, 5.0)
                                hGrow = Priority.ALWAYS
                            }
                        }
                        radiobutton("British Pound", this) {
                            action {
                                controller.currency = Currency.GBP
                                controller.setControlCurrency()
                            }
                            hboxConstraints {
                                padding = Insets(0.0, 5.0, 0.0, 5.0)
                                hGrow = Priority.ALWAYS
                            }
                        }
                        radiobutton("EURO", this) {
                            action {
                                controller.currency = Currency.EUR
                                controller.setControlCurrency()
                            }
                            hboxConstraints {
                                padding = Insets(0.0, 5.0, 0.0, 5.0)
                                hGrow = Priority.ALWAYS
                            }
                        }
                        selectToggle(dollar)
                    }
                }
        }
    }
        /**
         * Second Tab: shows the history of the prices since application started.
         */
        tab("History") {
            tableview (controller.datas){
                readonlyColumn("Time", Bitcoin::time).cellFormat { text = it.updated.substring(12,18) }
                readonlyColumn("USD", Bitcoin::bpi).cellFormat { text = String.format("%.2f", it.usd.rate_float)}
                readonlyColumn("GBP", Bitcoin::bpi).cellFormat { text = String.format("%.2f", it.gbp.rate_float)}
                readonlyColumn("EUR", Bitcoin::bpi).cellFormat { text = String.format("%.2f", it.eur.rate_float)}
            }
        }
    }
}