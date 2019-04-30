package com.example;

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
}
