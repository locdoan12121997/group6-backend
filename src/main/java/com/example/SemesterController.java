package com.example;

import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONObject;
import java.util.Collections;
import java.util.Map;


public class SemesterController {

    public JSONObject createSemester(String fromTime, String toTime) {
        String ans = "";
        try {
            final Map<String, ?> config = Collections.emptyMap();
            String pattern = "yyyy-MM-dd";
            Statement stm = com.example.Main.connection.createStatement();
            String sql = "CALL CreateSemester(" + fromTime +","+ toTime +");";
            ResultSet rs = stm.executeQuery(sql);
            JSONObject object = JsonSerializer.convertToJSON(rs);
            rs.close();
            stm.close();

            return object;
        }
        catch (Exception exp){
            ans = exp.toString();
            return null;
        }
    }

    public JSONObject getSemesters() {
        String ans = "";
        try {
            final Map<String, ?> config = Collections.emptyMap();
            String pattern = "yyyy-MM-dd";
            Statement stm = com.example.Main.connection.createStatement();
            String sql = "SELECT * FROM Semester;";
            ResultSet rs = stm.executeQuery(sql);
            JSONObject object = JsonSerializer.convertToJSON(rs);
            rs.close();
            stm.close();
            return object;
        }
        catch (Exception exp){
            ans = exp.toString();
            return null;
        }
    }
}
