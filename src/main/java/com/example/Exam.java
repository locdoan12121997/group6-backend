package com.example;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Exam {
    public void CreateExam(String date_of_exam, String from_time, String to_time, String deadline, int module_id){
        try {
            String query = String.format("CALL CreateExam('%s', '%s', '%s', '%s', %d);", date_of_exam, from_time, to_time, deadline, module_id);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteExam(int exam_id){
        try {
            String query = String.format("CALL DeleteExam(%d);", exam_id);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateExam(String date_of_exam, String from_time, String to_time, String deadline, int exam_id){
        try {
            String query = String.format("CALL UpdateExam(%d, '%s', '%s', '%s', '%s');", exam_id, date_of_exam, from_time, to_time, deadline);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public JSONObject GetExams(){
        try {
            String query = "CALL GetExams();";
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
    public JSONObject GetExamBySemesterId(int semesterId){
        try {
            String query = String.format("CALL GetExamBySemesterId(%d);", semesterId);
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

    public JSONObject GetExamById(int examId){
        try {
            String query = String.format("CALL GetExamById(%d);", examId);
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
