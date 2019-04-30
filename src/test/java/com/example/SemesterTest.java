package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class SemesterTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateSemester() throws Exception {
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM  Semester WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("1111-11-11", actual.getDate("from_time").toString());
            assertEquals("1212-12-12", actual.getDate("to_time").toString());
        }
        else fail();
        actual.close();
        Semester.DeleteSemester(last_id);
    }

    @Test
    public void testUpdateSemester() throws Exception {
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        int last_id = Main.LastInsertId();
        Semester.UpdateSemester(last_id, "1234-10-10", "2345-11-11");
        ResultSet actual = Main.getResultSet("SELECT * FROM  Semester WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("1234-10-10", actual.getDate("from_time").toString());
            assertEquals("2345-11-11", actual.getDate("to_time").toString());
        }
        else fail();
        actual.close();
        Semester.DeleteSemester(last_id);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        int last_id = Main.LastInsertId();
        Semester.DeleteSemester(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Semester WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
