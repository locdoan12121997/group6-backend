package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;

public class ModuleSession {
    public void CreateModuleSession(Date sessionDate, Time fromTime, Time toTime, int moduleId){
        try {
            String query = String.format("CALL CreateModuleSession('%s', '%s', %d);", sessionDate.toString(), fromTime.toString(), toTime.toString(), moduleId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteModuleSession(int moduleId){
        try {
            String query = String.format("DeleteModuleSession(%d)", moduleId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UpdateModuleSession(int moduleSessionId, Date sessionDate, Time fromTime, Time toTime){
        try {
            String query = String.format("CALL UpdateModuleSession('%s', '%s', %d);", sessionDate.toString(), fromTime.toString(), toTime.toString(), moduleSessionId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public JSONObject GetModuleSessionById(int sessionId){
        try {
            String query = String.format("CALL GetModuleSessionById(%d);", sessionId);
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
