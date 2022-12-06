package api.app

import javafx.scene.text.FontWeight
import tornadofx.*

/**
 * Stylesheet for GUI
 */
class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val price by cssclass()
        val main by cssclass()
    }
    init {
        main {
            backgroundColor += javafx.scene.paint.Color.DEEPPINK
        }
        heading {
            fontSize = 1.5.em
            fontWeight = FontWeight.BOLD
            padding = box(2.px)
        }
        price {
            fontSize = 2.em
            fontWeight = FontWeight.BOLD
        }
    }
}

