package com.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class StudentTest {


    public StudentTest() throws SQLException {

    }

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCRUD() throws Exception {
        //Create
        Students.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Student JOIN Account ON Student.account_id = Account.id WHERE Student.id = " + last_id);
        if (actual.next()) {
            String[] expectedStrings = {"username", "password", "firstname", "lastname", "code"};
            String[] actualStrings = {actual.getString("user_name"), actual.getString("user_password"), actual.getString("first_name"), actual.getString("last_name"), actual.getString("code")};
            assertArrayEquals(expectedStrings, actualStrings);
        }
        else fail();
        actual.close();
        //Verify
        assertFalse(!Students.VerifyStudentAccount("username", "password"));


        //Update
        Students.UpdateStudentCode(last_id, "code2");
        actual = Main.getResultSet("SELECT * FROM Student WHERE id = " + last_id + " AND code = 'code2'");
        if (!actual.next())  fail();
        actual.close();

        //Delete
        Students.DeleteStudentAccount(last_id);
        actual = Main.getResultSet("SELECT * FROM Student WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
