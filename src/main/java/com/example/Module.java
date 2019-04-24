package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Module {
    public JSONObject GetOverlapModules(){
        try {
            String query = "CALL GetOverlapModules();";
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
    public JSONObject GetEnrollsByModuleId(int moduleId){
        try {
            String query = String.format("CALL GetEnrollsByModuleId(%d);", moduleId);
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
    public JSONObject GetModuleDetails(int moduleId){
        try {
            String query = String.format("CALL GetModuleDetails(%d);", moduleId);
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


    public void CreateModule(String code, String name, int semesterId){
        try {
            String query = String.format("CALL CreateModule(%s, %s, %d);", code, name, semesterId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UpdateModule(int moduleId, String moduleCode, String moduleName){
        try {
            String query = String.format("CALL UpdateModule(%d, %s, %s);", moduleId, moduleCode, moduleName);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteModule(int moduleId){
        try {
            String query = String.format("CALL DeleteModule(%d);", moduleId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
