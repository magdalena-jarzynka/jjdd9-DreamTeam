package servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logging-servlet")
public class LoggingServlet extends HttpServlet {
    private static final Logger loggerServer = LoggerFactory.getLogger("com.infoshareacademy");
    private static final Logger loggerApp = LoggerFactory.getLogger("application");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        loggerServer.debug("Server debug \n");
        loggerServer.info("Server info \n");
        loggerServer.warn("Server warn \n");
        loggerServer.error("Server error \n");

        loggerApp.debug("Application file debug \n");
        loggerApp.info("Application file info \n");
        loggerApp.warn("Application file warn \n");
        loggerApp.error("Application file error \n");

        PrintWriter writer = resp.getWriter();
        writer.println("test");

    }
}
