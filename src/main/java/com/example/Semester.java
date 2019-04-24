package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import javax.json.*;
import java.text.DateFormat;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("semesters")
public class Semester {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getSemester() {
        String ans = "";
        try {
//            Statement stm = Main.connection.createStatement();
//            String sql = "SELECT * FROM Semester";
//            ResultSet rs = stm.executeQuery(sql);
//            JsonBuilderFactory factory = Json.createBuilderFactory(config);
////            JsonArray value = factory.createArrayBuilder();
//            String pattern = "yyyy-MM-dd HH:mm:ss";
//            DateFormat df = new SimpleDateFormat(pattern);
//            while (rs.next()){
//                int id = rs.getInt("id");
//                Date dbSqlDate = rs.getDate("from_time");
//                String todayAsString = df.format(dbSqlDate);
////                value.add(factory.createObjectBuilder()
////                                .add("id", id)
////                                .add("from_date", todayAsString));
//            }
//            rs.close();
//            stm.close();
//            JsonObject result = factory.createObjectBuilder()
//                    .add("data", value);
            JsonBuilderFactory factory = Json.createBuilderFactory(null);
            JsonObject value = factory.createObjectBuilder()
                    .add("firstName", "John")
                    .add("lastName", "Smith")
                    .add("age", 25)
                    .add("address", factory.createObjectBuilder()
                            .add("streetAddress", "21 2nd Street")
                            .add("city", "New York")
                            .add("state", "NY")
                            .add("postalCode", "10021"))
                    .add("phoneNumber", factory.createArrayBuilder()
                            .add(factory.createObjectBuilder()
                                    .add("type", "home")
                                    .add("number", "212 555-1234"))
                            .add(factory.createObjectBuilder()
                                    .add("type", "fax")
                                    .add("number", "646 555-4567")))
                    .build();
            return value.toString();
        }
        catch (Exception exp){
            ans = exp.toString();
            return null;
        }
    }
}
