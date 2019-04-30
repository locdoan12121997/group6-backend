package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;

public class ModuleSession {
    public static void CreateModuleSession(String sessionDate, String fromTime, String toTime, int moduleId){
        try {
            String query = String.format("CALL CreateModuleSession('%s', '%s', '%s', %d);", sessionDate, fromTime, toTime, moduleId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DeleteModuleSession(int moduleId){
        try {
            String query = String.format("CALL DeleteModuleSession(%d)", moduleId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void UpdateModuleSession(int moduleSessionId, String sessionDate, String fromTime, String toTime){
        try {
            String query = String.format("CALL UpdateModuleSession(%d, '%s', '%s', '%s');", moduleSessionId, sessionDate, fromTime, toTime);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
