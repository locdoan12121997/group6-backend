package com.example;

public class Attendance {
    public static void CreateAttendance(int studentId, int sessionId){
        try {
            String query = String.format("CALL CreateAttendance(%d, %d);", studentId, sessionId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DeleteAttendance(int studentId, int sessionId){
        try {
            String query = String.format("CALL DeleteAttendance(%d, %d);", studentId, sessionId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
