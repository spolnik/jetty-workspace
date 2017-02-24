package hello.jetty

import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.handler.ContextHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.jetty.server.handler.ContextHandlerCollection



class HelloWorldHandler(val greeting: String = "Hello World", val body: String? = null) : AbstractHandler() {

    override fun handle(
            target: String,
            baseRequest: Request,
            request: HttpServletRequest,
            response: HttpServletResponse
    ) {
        response.contentType = "text/html; charset=utf-8"
        response.status = HttpServletResponse.SC_OK

        val out = response.writer
        out.println("<h1>$greeting</h1>")

        if (body != null) {
            out.println(body)
        }

        baseRequest.isHandled = true
    }

}

fun main(args: Array<String>) {
    val context = ContextHandler("/").apply {
        contextPath = "/"
        handler = HelloWorldHandler("Root Hello", "<p>/</p>")
    }

    val contextFR = ContextHandler("/fr").apply {
        handler = HelloWorldHandler("Bonjoir", "<p>/fr</p>")
    }

    val contextIT = ContextHandler("/it").apply {
        handler = HelloWorldHandler("Bongiorno", "<p>/it</p>")
    }

    val contextV = ContextHandler("/").apply {
        virtualHosts = arrayOf("127.0.0.2")
        handler = HelloWorldHandler("Virtual Hello", "<p>127.0.0.2/fr</p>")
    }

    val contexts = ContextHandlerCollection().apply {
        handlers = arrayOf<Handler>(context, contextFR, contextIT, contextV)
    }

    val server = Server(8081).apply {
        handler = contexts
    }

    server.start()
    server.join()
}
