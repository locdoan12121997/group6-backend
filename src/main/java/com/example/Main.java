package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.*;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";
    static final String DB_URL = "jdbc:mysql://localhost:3306/register_db";
    static final String USER = "root";
    static final String PASS = "1234";
    static Connection connection = null;
    static Statement statement = null;

    public static void establishConnection() throws SQLException, ClassNotFoundException{
        if (connection != null) return;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public static ResultSet getResultSet(String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public static int LastInsertId() throws SQLException{
        ResultSet resultSet = getResultSet("SELECT LAST_INSERT_ID();");
        resultSet.next();
        return resultSet.getInt("LAST_INSERT_ID()");
    }

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
    public static void main(String[] args) throws IOException, SQLException {
        try {
            establishConnection();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        finally {
            System.out.println("Connecting to jersey database");
            //final HttpServer server = startServer();
            //System.in.read();
            //server.stop();

        }
    }
}

