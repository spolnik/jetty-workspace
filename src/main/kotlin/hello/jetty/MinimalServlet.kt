package hello.jetty

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MinimalServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.status = HttpServletResponse.SC_OK
        resp.writer.println("<h1>Hello from MinimalServlet</h1>")
    }
}

fun main(args: Array<String>) {


    val servletHandler = ServletHandler().apply {
        addServletWithMapping(MinimalServlet::class.java, "/*")
    }

    val server = Server(8083).apply {
        handler = servletHandler
    }

    server.start()
    server.join()
}
