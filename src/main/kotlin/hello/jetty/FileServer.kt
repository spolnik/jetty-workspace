package hello.jetty

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.DefaultHandler
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler

fun main(args: Array<String>) {

    val resourceHandler = ResourceHandler().apply {
        isDirectoriesListed = true
        welcomeFiles = arrayOf("index.html")
        resourceBase = "."
    }

    val handlers = HandlerList().apply {
        handlers = arrayOf(resourceHandler, DefaultHandler())
    }

    val server = Server(8082).apply {
        handler = handlers
    }

    server.start()
    server.join()
}
