package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Student {
    public static JSONObject GetStudents(){
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

    public static JSONObject GetStudentById(int studentId){
        try {
            String query = String.format("CALL GetStudentById(%d);", studentId);
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

    public static void UpdateStudentCode(int studentId, String studentCode){
        try {
            String query = String.format("CALL UpdateStudentCode(%d, '%s');", studentId, studentCode);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DeleteStudentAccount(int student_id){
        try {
            String query = String.format("CALL DeleteStudentAccount(%d);", student_id);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void CreateStudentAccount(String userName, String userPassword, String firstName, String lastName, String studentCode){
        try {
            String query = String.format("CALL CreateStudentAccount('%s', '%s', '%s', '%s', '%s');", userName, userPassword, firstName, lastName, studentCode);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean VerifyStudentAccount(String userName, String userPassword){
        try {
            String query = String.format("CALL VerifyStudentAccount('%s', '%s');", userName, userPassword);
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
    public static JSONObject GetAttendanceListBySessionId(int sessionId){
        try {
            String query = String.format("CALL GetAttendanceListBySessionId(%d);", sessionId);
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
    public static JSONObject GetRegisterByExamId(int examId){
        try {
            String query = String.format("CALL GetRegisterByExamId(%d);", examId);
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
