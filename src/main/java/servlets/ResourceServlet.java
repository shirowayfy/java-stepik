package servlets;

import resources.TestResource;
import resources.controller.ResourceServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sax.ReadXMLFileSAX.readXML;

public class ResourceServlet extends HttpServlet {

    public static final String URL_PAGE = "/resources";

    public final ResourceServer resourceServer;

    public ResourceServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getParameter("path");

        if (path == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Object object = readXML(path);
        if(object == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resourceServer.changeTestResource((TestResource) object );
        resp.setStatus(HttpServletResponse.SC_OK);


    }


}
