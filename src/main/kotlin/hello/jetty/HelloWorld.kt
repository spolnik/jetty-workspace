package hello.jetty

import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloWorld : AbstractHandler() {

    override fun handle(
            target: String,
            baseRequest: Request,
            request: HttpServletRequest,
            response: HttpServletResponse
    ) {
        response.contentType = "text/html; charset=utf-8"
        response.status = HttpServletResponse.SC_OK

        response.writer.println("<h1>Hello World</h1>")
        baseRequest.isHandled = true
    }

}

fun main(args: Array<String>) {
    val server = Server(8081)
    server.handler = HelloWorld()

    server.start()
    server.join()
}
