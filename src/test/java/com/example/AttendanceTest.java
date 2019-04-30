package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class AttendanceTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAttendance() throws Exception {
        Attendance.CreateAttendance(1234, 5678);
        ResultSet actual = Main.getResultSet("SELECT * FROM Attendance" +
                " WHERE student_id = 1234 session_id = 5678");
        if (actual.next()) {
            assertEquals(1234,actual.getInt("student_id"));
            assertEquals(5678,actual.getInt("session_id"));
        }
        else fail();
        actual.close();
        Attendance.DeleteAttendance(1234,5678);

    }

    @Test
    public void testDeleteAttendance() throws Exception {
        Attendance.CreateAttendance(1234, 5678);
        Attendance.DeleteAttendance(1234,5678);
        ResultSet actual = Main.getResultSet("SELECT * FROM Attendance" +
                " WHERE student_id = 1234 session_id = 5678");
        if (actual.next()) fail();
        actual.close();
    }
}
