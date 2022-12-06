package api.app

import api.view.MainView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus

/**
 * Parsing View to app
 */
class TornadoApp : App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {
        stage.width = 330.0
        stage.height = 500.0
        super.start(stage)
    }
    init {
        reloadStylesheetsOnFocus()
    }

}