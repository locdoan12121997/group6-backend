package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class ExamTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", 1234);
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Exam " +
                "WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("1111-11-20", actual.getDate("date_of_exam").toString());
            assertEquals("1111-11-11", actual.getDate("deadline").toString());
            assertEquals("08:00:00", actual.getTime("from_time").toString());
            assertEquals("10:00:00", actual.getTime("to_time").toString());
            assertEquals(1234,actual.getInt("module_id"));
        }
        else fail();
        actual.close();
        Exam.DeleteExam(last_id);
    }

    @Test
    public void testUpdateExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", 1234);
        int last_id = Main.LastInsertId();
        Exam.UpdateExam(last_id, "1111-11-21", "09:00:00", "11:00:00", "1111-11-12");
        ResultSet actual = Main.getResultSet("SELECT * FROM Exam" +
                "WHERE id = " +last_id);
        if (actual.next()) {
            assertEquals("1111-11-21", actual.getDate("date_of_exam").toString());
            assertEquals("1111-11-12", actual.getDate("deadline").toString());
            assertEquals("09:00:00", actual.getTime("from_time").toString());
            assertEquals("11:00:00", actual.getTime("to_time").toString());
            assertEquals(1234,actual.getInt("module_id"));
        }
        else fail();
        actual.close();
        Exam.DeleteExam(last_id);
    }


    @Test
    public void testDeleteExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", 1234);
        int last_id = Main.LastInsertId();
        Exam.DeleteExam(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Semester WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
