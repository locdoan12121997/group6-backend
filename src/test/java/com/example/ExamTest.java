package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class ExamTest {

    int semester_id, module_id;
    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Semester.CreateSemester("1111-11-01", "1111-11-11");
        semester_id = Main.LastInsertId();
        Module.CreateModule("test module", "testing", semester_id);
        module_id = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        Module.DeleteModule(module_id);
        Semester.DeleteSemester(semester_id);
    }

    @Test
    public void testCreateExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", module_id);
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Exam WHERE id = " + last_id + ";");
        if (actual.next()) {
            assertEquals("1111-11-20", actual.getString("date_of_exam"));
            assertEquals("1111-11-11", actual.getString("deadline"));
            assertEquals("08:00:00", actual.getString("from_time"));
            assertEquals("10:00:00", actual.getString("to_time"));
            assertEquals(module_id,actual.getInt("module_id"));
        }
        else {
            fail();
        }
        actual.close();
        Exam.DeleteExam(last_id);
    }

    @Test
    public void testUpdateExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", module_id);
        int last_id = Main.LastInsertId();
        Exam.UpdateExam(last_id, "1111-11-21", "09:00:00", "11:00:00", "1111-11-12");
        ResultSet actual = Main.getResultSet("SELECT * FROM Exam WHERE id = " + last_id + ";");
        if (actual.next()) {
            assertEquals("1111-11-21", actual.getString("date_of_exam"));
            assertEquals("1111-11-12", actual.getString("deadline"));
            assertEquals("09:00:00", actual.getString("from_time"));
            assertEquals("11:00:00", actual.getString("to_time"));
            assertEquals(module_id,actual.getInt("module_id"));
        }
        else fail();
        actual.close();
        Exam.DeleteExam(last_id);
    }


    @Test
    public void testDeleteExam() throws Exception {
        Exam.CreateExam("1111-11-20", "08:00:00", "10:00:00", "1111-11-11", module_id);
        int last_id = Main.LastInsertId();
        Exam.DeleteExam(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Exam WHERE id = " + last_id + ";");
        if (actual.next()) fail();
        actual.close();
    }
}
