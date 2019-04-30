package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class RegisterTest {

    int semesterId, moduleId, examId, studentId;

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        semesterId = Main.LastInsertId();
        Module.CreateModule("code", "name", semesterId);
        moduleId = Main.LastInsertId();
        Exam.CreateExam("1212-12-12", "08:00:00", "09:00:00", "1212-12-10", moduleId);
        examId = Main.LastInsertId();
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        studentId = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        Student.DeleteStudentAccount(studentId);
        Exam.DeleteExam(examId);
        Module.DeleteModule(moduleId);
        Semester.DeleteSemester(semesterId);
    }

    @Test
    public void testCreateRegister() throws Exception {
        Register.CreateRegister(studentId, examId);
        ResultSet actual = Main.getResultSet(String.format("SELECT * FROM RegisterList WHERE student_id = %d AND exam_id = %d", studentId, examId));
        if (actual.next()) {
            assertEquals(studentId, actual.getInt("student_id"));
            assertEquals(examId, actual.getInt("exam_id"));
        }
        else fail();
        actual.close();
        Register.DeleteRegister(studentId, examId);
    }

    @Test
    public void testDeleteRegister() throws Exception {
        Register.CreateRegister(studentId, examId);
        Register.DeleteRegister(studentId, examId);
        ResultSet actual = Main.getResultSet(String.format("SELECT * FROM RegisterList WHERE student_id = %d AND exam_id = %d", studentId, examId));
        if (actual.next()) fail();
        actual.close();
    }
}