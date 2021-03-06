package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class StudentTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Student JOIN Account ON Student.account_id = Account.id WHERE Student.id = " + last_id);
        if (actual.next()) {
            String[] expectedStrings = {"username", "password", "firstname", "lastname", "code"};
            String[] actualStrings = {actual.getString("user_name"), actual.getString("user_password"), actual.getString("first_name"), actual.getString("last_name"), actual.getString("code")};
            assertArrayEquals(expectedStrings, actualStrings);
        }
        else fail();
        actual.close();
        Student.DeleteStudentAccount(last_id);

    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        int last_id = Main.LastInsertId();
        Student.UpdateStudentCode(last_id, "code4");
        ResultSet actual = Main.getResultSet("SELECT * FROM Student WHERE id = " + last_id + " AND code = 'code4'");
        if (!actual.next())  fail();
        actual.close();
        Student.DeleteStudentAccount(last_id);
    }

    @Test
    public void testVerifyStudent() throws Exception {
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        int last_id = Main.LastInsertId();
        assertFalse(!Student.VerifyStudentAccount("username", "password"));
        assertFalse(Student.VerifyStudentAccount("wrongusername", "wrongpassword"));
        Student.DeleteStudentAccount(last_id);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        int last_id = Main.LastInsertId();
        Student.DeleteStudentAccount(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Student WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
