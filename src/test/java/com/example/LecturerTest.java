package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class LecturerTest {

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
        Lecturer.CreateLecturerAccount("username", "password", "firstname", "lastname");
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Lecturer JOIN Account ON Lecturer.account_id = Account.id WHERE Lecturer.id = " + last_id);
        if (actual.next()) {
            String[] expectedStrings = {"username", "password", "firstname", "lastname"};
            String[] actualStrings = {actual.getString("user_name"), actual.getString("user_password"), actual.getString("first_name"), actual.getString("last_name")};
            assertArrayEquals(expectedStrings, actualStrings);
        }
        else fail();
        actual.close();

        //Verify
        assertFalse(!Lecturer.VerifyLecturerAccount("username", "password"));
        assertFalse(Lecturer.VerifyLecturerAccount("wrongusername", "wrongpassword"));


        //Delete
        Lecturer.DeleteLecturerAccount(last_id);
        actual = Main.getResultSet("SELECT * FROM Lecturer WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
