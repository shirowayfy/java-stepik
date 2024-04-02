package main;

import accounts.AccountService;
import dbService.DBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorRequestsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;


public class Main {
    public static void main(String[] args) throws Exception {

        DBService dbService = new DBService();
        dbService.printConnectInfo();


        AccountService accountService = new AccountService(dbService);



        MirrorRequestsServlet mirrorRequestsServlet = new MirrorRequestsServlet();
        SignInServlet signInServlet = new SignInServlet(accountService);
        SignUpServlet signUpServlet = new SignUpServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorRequestsServlet), "/mirror");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
        //System.out.print();
    }
}
