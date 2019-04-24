package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.*;
import org.json.JSONObject;
import com.example.SemesterController;
/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/myapp/";
    static final String DB_URL = "jdbc:mysql://localhost:3306/register_db";
    static final String USER = "root";
    static final String PASS = "loc123";
    static Connection connection = null;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.example");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to jersey database");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = null;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        finally {
            System.out.println("Connecting to jersey database");
//            final HttpServer server = startServer();

        }
        SemesterController ctrl = new SemesterController();
        JSONObject json = ctrl.getSemesters();

        try{
            System.out.println(json.toString('\t'));
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}

