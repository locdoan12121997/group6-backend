package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.sql.ResultSet;

public class JsonSerializer {

    public static JSONObject convertToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                if (columnValue == null){
                    columnValue = "null";
                }
                if (obj.has(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.put(obj);
        }
        JSONObject result = new JSONObject();
        result.put("data", jsonArray);
        return result;
    }

    public static void writeToJSONFile(JSONArray[] resultArray, String filepath) throws Exception{
        if(resultArray.length>0) {
            FileWriter file = new FileWriter("json_file_test.json");

            for(int i=0; i<resultArray.length; i++) {
                file.write(resultArray[i].toString('\t'));
                file.write("\n");
            }
            file.close();
        }
    }
}
