package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Students {
    public JSONObject GetStudents(){
        try {
            String query = "CALL GetStudents();";
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
    public JSONObject GetRegistersByStudentId(int studentId){
        try {
            String query = String.format("CALL GetRegistersByStudentId(%d);", studentId);
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
    public JSONObject GetModulesByStudentId(int studentId){
        try {
            String query = String.format("CALL GetModulesByStudentId(%d);", studentId);
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

    public void UpdateStudentCodeByStudentId(int studentId, String studentCode){
        try {
            String query = String.format("CALL UpdateStudentCodeByStudentId(%d, %s);", studentId, studentCode);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteStudentAccount(int student_id){
        try {
            String query = String.format("CALL DeleteStudentByStudentId(%d);", student_id);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CreateStudentAccount(String userName, String userPassword, String firstName, String lastName, String studentCode){
        try {
            String query = String.format("CALL CreateStudentAccount(%s, %s, %s, %s, %s);", userName, userPassword, firstName, lastName, studentCode);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean VerifyStudentAccount(String userName, String userPassword){
        try {
            String query = String.format("CALL VerifyStudentAccount(%s, %s);", userName, userPassword);
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
