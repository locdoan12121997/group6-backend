package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.Statement;

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
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        String ans = "";
        try {
            Statement stm = Main.connection.createStatement();
            String sql = "SELECT id, name FROM Persons";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ans += id + ":\t" + name + "\n";
            }
            rs.close();
            stm.close();
        }
        catch (Exception exp){
            ans = exp.toString();
        }
        return ans;
    }
}
