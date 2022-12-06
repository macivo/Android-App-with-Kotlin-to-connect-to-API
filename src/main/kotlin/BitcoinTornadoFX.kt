/** Programming 1 with Kotlin - HS 19/20,
 *  Computer Science, Bern University of Applied Sciences */
/**
 * Hello. Welcome to bitcoin price application
 * This application get the bitcoin prices with 2 currencies
 * The Histories of the preises are update every minute with coroutine.
 * Mac Müller
 */

import api.app.TornadoApp
import tornadofx.launch

/** start the gui*/
fun main(args: Array<String>)  {
    launch<TornadoApp>(args)
}

