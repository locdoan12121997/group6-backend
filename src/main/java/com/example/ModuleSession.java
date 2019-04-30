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
    public void UpdateModuleSession(int moduleSessionId, Date sessionDate, Time fromTime, Time toTime){
        try {
            String query = String.format("CALL UpdateModuleSession('%s', '%s', %d);", sessionDate.toString(), fromTime.toString(), toTime.toString(), moduleSessionId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
