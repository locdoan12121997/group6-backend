package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Register {
    public void CreateRegister(int studentId, int examId){
        try {
            String query = String.format("CALL CreateRegister(%d, %d);", studentId, examId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteRegister(int studentId, int examId){
        try {
            String query = String.format("CALL DeleteRegister(%d, %d);", studentId, examId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public JSONObject GetRegisters(){
        try {
            String query = "CALL GetRegisters();";
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
