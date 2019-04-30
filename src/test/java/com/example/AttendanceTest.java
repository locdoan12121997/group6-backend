package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class AttendanceTest {

    int semesterId, moduleId, sessionId, studentId;

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code");
        studentId = Main.LastInsertId();
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        semesterId = Main.LastInsertId();
        Module.CreateModule("code", "name", semesterId);
        moduleId = Main.LastInsertId();
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:00", "12:00:00", moduleId);
        sessionId = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        ModuleSession.DeleteModuleSession(sessionId);
        Module.DeleteModule(moduleId);
        Semester.DeleteSemester(semesterId);
        Student.DeleteStudentAccount(studentId);
    }

    @Test
    public void testCreateAttendance() throws Exception {
        Attendance.CreateAttendance(studentId, sessionId);
        ResultSet actual = Main.getResultSet(String.format("SELECT * FROM Attendance WHERE student_id = %d AND session_id = %d", studentId, sessionId));
        if (actual.next()) {
            assertEquals(studentId,actual.getInt("student_id"));
            assertEquals(sessionId,actual.getInt("session_id"));
        }
        else fail();
        actual.close();
        Attendance.DeleteAttendance(studentId, sessionId);
    }

    @Test
    public void testDeleteAttendance() throws Exception {
        Attendance.CreateAttendance(studentId, sessionId);
        Attendance.DeleteAttendance(studentId, sessionId);
        ResultSet actual = Main.getResultSet(String.format("SELECT * FROM Attendance WHERE student_id = %d AND session_id = %d", studentId, sessionId));
        if (actual.next()) fail();
        actual.close();
    }
}