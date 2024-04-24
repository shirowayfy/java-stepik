package main;

import accounts.AccountServiceImpl;
import accounts.controller.AccountServerController;
import accounts.controller.AccountServerControllerMBean;
import base.AccountService;
import base.DBService;
import dbService.DBServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.controller.ResourceServer;
import resources.controller.ResourceServerMBean;
import servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;


public class Main {
    public static void main(String[] args) throws Exception {

        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();

        AccountService accountService = new AccountServiceImpl(dbService);

        MirrorRequestsServlet mirrorRequestsServlet = new MirrorRequestsServlet();
        SignInServlet signInServlet = new SignInServlet(accountService);
        SignUpServlet signUpServlet = new SignUpServlet(accountService);


        AccountServerControllerMBean serverStatistics = new AccountServerController(accountService);

        ResourceServerMBean resourceServer = new ResourceServer();

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName nameAccountServer = new ObjectName("Admin:type=AccountServerController");
        ObjectName nameResourceServer = new ObjectName("Admin1:type=ResourceServer");
        mbs.registerMBean(serverStatistics, nameAccountServer);
        mbs.registerMBean(resourceServer, nameResourceServer);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(mirrorRequestsServlet), "/mirror");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(new AdminServlet(accountService)), "/admin");

        context.addServlet(new ServletHolder(new ResourceServlet((ResourceServer) resourceServer)), ResourceServlet.URL_PAGE);

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
        //System.out.print();
    }
}
