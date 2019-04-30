package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Semester {
    public static void CreateSemester(String fromTime, String toTime){
        try {
            String query = String.format("CALL CreateSemester('%s', '%s');", fromTime, toTime);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DeleteSemester(int semesterId){
        try {
            String query = String.format("CALL DeleteSemester(%d);", semesterId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void UpdateSemester(int semesterId, String fromTime, String toTime){
        try {
            String query = String.format("CALL UpdateSemester(%d, '%s', '%s');", semesterId, fromTime, toTime);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JSONObject GetSemesterById(int semesterId){
        try {
            String query = String.format("CALL GetSemesterById(%d);", semesterId);
            ResultSet resultSet = Main.getResultSet(query);
            JSONObject jsonObject = JsonSerializer.convertToJSON(resultSet);
            resultSet.close();
            return jsonObject;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
