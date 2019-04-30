package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class RegisterTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateRegister() throws Exception {
        Register.CreateRegister(1234, 5678);
        ResultSet actual = Main.getResultSet("SELECT * FROM RegisterList" +
                " WHERE student_id = 1234 exam_id = 5678");
        if (actual.next()) {
            assertEquals(1234,actual.getInt("student_id"));
            assertEquals(5678,actual.getInt("exam_id"));
        }
        else fail();
        actual.close();
        Register.DeleteRegister(1234,5678);

    }

    @Test
    public void testDeleteRegister() throws Exception {
        Register.CreateRegister(1234, 5678);
        Register.DeleteRegister(1234,5678);
        ResultSet actual = Main.getResultSet("SELECT * FROM RegisterList" +
                " WHERE student_id = 1234 exam_id = 5678");
        if (actual.next()) fail();
        actual.close();
    }
}
