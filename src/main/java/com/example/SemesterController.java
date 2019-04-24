package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;
import java.text.SimpleDateFormat;


public class SemesterController {

    public SemesterController() throws SQLException {
    }

    Statement stm = Main.connection.createStatement();
    String sql = null;
    ResultSet rs = null;


    public void createSemester(String fromTime, String toTime) {
        String ans = "";
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            fromTime = simpleDateFormat.format(fromTime);
            toTime = simpleDateFormat.format(toTime);
            Statement stm = Main.connection.createStatement();
            sql = "CALL CreateSemester(" + fromTime +","+ toTime +");";
            rs = stm.executeQuery(sql);
            rs.close();
            stm.close();

        }
        catch (Exception exp){
            ans = exp.toString();
        }
    }

    public void updateSemester(int semesterId, String fromTime, String toTime) {
        String ans = "";
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            fromTime = simpleDateFormat.format(fromTime);
            toTime = simpleDateFormat.format(toTime);
            Statement stm = Main.connection.createStatement();
            sql = "CALL UpdateSemester(" + semesterId +"," + fromTime +","+ toTime +");";
            rs = stm.executeQuery(sql);
            rs.close();
            stm.close();

        }
        catch (Exception exp){
            ans = exp.toString();
        }
    }

    public JSONObject getSemesters() {
        String ans = "";
        try {
            sql = "SELECT * FROM Semester;";
            rs = stm.executeQuery(sql);
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

    public void deleteSemesters(int id) {
        String ans = "";
        try {
            sql = "SELECT * FROM Semester;";
            ResultSet rs = stm.executeQuery(sql);
            JSONObject object = JsonSerializer.convertToJSON(rs);
            rs.close();
            stm.close();
        }
        catch (Exception exp){
            ans = exp.toString();
        }
    }
}
