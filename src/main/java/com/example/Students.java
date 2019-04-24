package com.example;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class Students {

    public JsonObject GetModuleByStudentId(int student_id) {
        JsonBuilderFactory factory = Json.createBuilderFactory(Main.config);
        JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL GetModuleByStudentId(" + student_id +");";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int module_id, semester_id;
                String module_code, module_name;
                module_id   = rs.getInt("module_id");
                semester_id = rs.getInt("semester_id");
                module_code = rs.getString("code");
                module_name = rs.getString("name");
                JsonObjectBuilder object = factory.createObjectBuilder()
                        .add("moduleId", module_id)
                        .add("moduleCode", module_code)
                        .add("moduleName", module_name)
                        .add("semesterId", semester_id);
                jsonArrayBuilder.add(object);
            }
            rs.close();
            stm.close();
            return factory.createObjectBuilder().add("Module list: ", jsonArrayBuilder.build()).build();
        }
        catch (SQLException sqle){
            return null;
        }
    }
    public JsonObject GetRegistersByStudentId(int student_id){
        JsonBuilderFactory factory = Json.createBuilderFactory(Main.config);
        JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL GetRegisitersByStudentId(" + student_id + ");";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String module_code, module_name;
                Date exam_date;
                module_code = rs.getString("code");
                module_name = rs.getString("name");
                exam_date = rs.getDate("date_of_exam");
                JsonObjectBuilder object = factory.createObjectBuilder()
                        .add("moduleCode", module_code)
                        .add("moduleName", module_name)
                        .add("examDate", exam_date.toString());
                jsonArrayBuilder.add(object);
            }
            rs.close();
            stm.close();
            return factory.createObjectBuilder().add("Register list: ", jsonArrayBuilder.build()).build();
        }
        catch (SQLException sqle){
            return null;
        }
    }
    public JsonObject GetModulesByStudentId(int student_id){
        JsonBuilderFactory factory = Json.createBuilderFactory(Main.config);
        JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL GetModulesByStudentId(" + student_id + ");";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String module_code, module_name;
                module_code = rs.getString("code");
                module_name = rs.getString("name");
                JsonObjectBuilder object = factory.createObjectBuilder()
                        .add("moduleCode", module_code)
                        .add("moduleName", module_name);
                jsonArrayBuilder.add(object);
            }
            rs.close();
            stm.close();
            return factory.createObjectBuilder().add("Module list: ", jsonArrayBuilder.build()).build();
        }
        catch (SQLException sqle){
            return null;
        }
    }
    public JsonObject GetStudentlist(){
        JsonBuilderFactory factory = Json.createBuilderFactory(Main.config);
        JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL GetModulesByStudentId();";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int accountId;
                String studentCode, userName, userPassword, firstName, lastName;
                accountId = rs.getInt("account_id");
                studentCode = rs.getString("student_code");
                userName = rs.getString("user_name");
                userPassword = rs.getString("user_password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                JsonObjectBuilder object = factory.createObjectBuilder()
                        .add("account_id", accountId)
                        .add("student_code", studentCode)
                        .add("user_name", userName)
                        .add("user_password", userPassword)
                        .add("first_name", firstName)
                        .add("last_name", lastName);
                jsonArrayBuilder.add(object);
            }
            rs.close();
            stm.close();
            return factory.createObjectBuilder().add("Student list: ", jsonArrayBuilder.build()).build();
        }
        catch (SQLException sqle){
            return null;
        }
    }

    public void UpdateStudentCodeByStudentId(int student_id, String studentCode){
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL UpdateStudentCode(" + student_id + ", " + studentCode + ");";
            stm.executeQuery(query);
            stm.close();
        }
        catch (SQLException sqle){
        }
    }
    public void DeleteStudentByStudentId(int student_id){
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL DeleteStudentByStudentId(" + student_id + ");";
            stm.executeQuery(query);
            stm.close();
        }
        catch (SQLException sqle){
        }
    }
    public boolean FindStudentAccount(String userName, String userPassword){
        try {
            Statement stm = Main.connection.createStatement();
            String query = "CALL FindStudentAccount(" + userName + ", " + userPassword + ");";
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) return true;
            return false;
        }
        catch (SQLException sqle){
            return false;
        }
    }


}
