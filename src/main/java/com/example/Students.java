package com.example;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;

@Path("students/{studentid}")
public class Students {

    @GET
    @Path("/enrolledmodules")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetModuleByStudentId(@PathParam("studentid") String student_id) {
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);

        JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
        String ans;
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL GetModuleByStudentId(" + student_id +");";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int module_id, semester_id;
                String module_code, module_name;
                module_id   = rs.getInt("module_id");
                semester_id = rs.getInt("semester_id");
                module_code = rs.getString("code");
                module_name = rs.getString("name");
                JsonObjectBuilder object = factory.createObjectBuilder()
                        .add("moduleId", module_id)
                        .add("moduleCode", module_code)
                        .add("moduleName", module_name)
                        .add("semesterId", semester_id);
                jsonArrayBuilder.add(object);
            }
            JsonObject jsonObject = factory.createObjectBuilder().add("Module list: ", jsonArrayBuilder.build()).build();
            rs.close();
            stm.close();
            return jsonObject.toString();
        }
        catch (SQLException sqle){
            ans = sqle.toString();
        }
        return "";
    }

}
