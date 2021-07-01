package ua.com.alevel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "servlet", urlPatterns = "/servlet")
public class ServletController extends HttpServlet {

    private static final long serialVersionUID = -8948379822734246956L;

    private Map<String, String> MapForIpAndHeaders = new ConcurrentHashMap<>();

    private static final Logger log = LoggerFactory.getLogger(ServletController.class);

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentIP = req.getRemoteHost();
        String currentHead = req.getHeader("User-Agent");
        MapForIpAndHeaders.put(currentIP, currentHead);

        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<ul>");

        for (Map.Entry<String, String> entry : MapForIpAndHeaders.entrySet()) {
            if (entry.getKey().equals(currentIP))
                responseBody.println("<b>");
            responseBody.println("<li>" + entry.getKey()
                    + " :: "
                    + entry.getValue() + "</b></li>");
        }
        responseBody.println("</ul>");
    }

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}