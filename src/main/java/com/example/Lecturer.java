package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Lecturer {
    public static JSONObject GetLecturers(){
        try {
            String query = String.format("CALL GetLecturers();");
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
    public static JSONObject GetModulesByLecturerId(int lecturerId){
        try {
            String query = String.format("CALL GetModulesByLecturerId(%d);", lecturerId);
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

    public static void CreateLecturerAccount(String userName, String userPassword, String firstName, String lastName){
        try {
            String query = String.format("CALL CreateLecturerAccount('%s', '%s', '%s', '%s');", userName, userPassword, firstName, lastName);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DeleteLecturerAccount(int lecturerId){
        try {
            String query = String.format("CALL DeleteLecturerAccount(%d);", lecturerId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean VerifyLecturerAccount(String userName, String userPassword){
        try {
            String query = String.format("CALL VerifyLecturerAccount('%s', '%s');", userName, userPassword);
            ResultSet resultSet = Main.getResultSet(query);
            if (resultSet.next()) {
                resultSet.close();
                return true;
            }
            resultSet.close();
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
