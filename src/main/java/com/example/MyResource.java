package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.json.*;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        String ans = "";
        try {
            Statement stm = Main.connection.createStatement();
            String sql = "SELECT id FROM Semester";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                ans += id + "\n";
            }
            rs.close();
            stm.close();
        }
        catch (Exception exp){
            System.out.println(exp.toString());
        }
        return ans;
    }
}
