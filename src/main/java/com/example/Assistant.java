package com.example;


import java.sql.ResultSet;

public class Assistant {
    public void CreateAssistantAccount(String userName, String userPassword, String firstName, String lastName){
        try {
            String query = String.format("CALL CreateAssistantAccount(%s, %s, %s, %s, %s);", userName, userPassword, firstName, lastName);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteAssistantAccount(int assistantId){
        try {
            String query = String.format("CALL DeleteAssistantAccount(%d);", assistantId);
            Main.getResultSet(query).close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean VerifyAssistantAccount(String userName, String userPassword){
        try {
            String query = String.format("CALL VerifyAssistantAccount(%s, %s);", userName, userPassword);
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
}
