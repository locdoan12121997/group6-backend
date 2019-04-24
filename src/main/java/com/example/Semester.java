package com.example;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import javax.json.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

public class Semester {

    public JsonObject getSemester() {
        String ans = "";
        try {
            final Map<String, ?> config = Collections.emptyMap();
            Statement stm = Main.connection.createStatement();
            String sql = "CALL DeleteLecturer()";
            ResultSet rs = stm.executeQuery(sql);
            JsonBuilderFactory factory = Json.createBuilderFactory(config);
            JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            while (rs.next()){
                int id = rs.getInt("id");
                Date dbSqlDate = rs.getDate("from_time");
                String todayAsString = df.format(dbSqlDate);
                JsonObjectBuilder object = factory.createObjectBuilder()
                                .add("id", id)
                                .add("from_date", todayAsString);
                jsonArrayBuilder.add(object);
            }
            rs.close();
            stm.close();
            JsonObject object = factory.createObjectBuilder()
                    .add("data", jsonArrayBuilder.build())
                    .build();
            return jsonArrayBuilder.toString();
        }
        catch (Exception exp){
            ans = exp.toString();
            return null;
        }
    }
}
