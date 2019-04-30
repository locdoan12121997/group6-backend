package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class AttendanceTest {

    int semester_id, module_id, session_id, student_id;

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Semester.CreateSemester("1111-11-01", "1111-11-11");
        semester_id = Main.LastInsertId();
        Module.CreateModule("test module 3", "testing", semester_id);
        module_id = Main.LastInsertId();
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:00", "12:00:00", module_id);
        session_id = Main.LastInsertId();
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "code 5");
        student_id = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        Student.DeleteStudentAccount(student_id);
        ModuleSession.DeleteModuleSession(session_id);
        Module.DeleteModule(module_id);
        Semester.DeleteSemester(semester_id);
    }

    @Test
    public void testCreateAttendance() throws Exception {
        Attendance.CreateAttendance(student_id, session_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Attendance" +
                " WHERE student_id = " + student_id + " session_id = " + student_id + ";");
        if (actual.next()) {
            assertEquals(student_id,actual.getInt("student_id"));
            assertEquals(session_id,actual.getInt("session_id"));
        }
        else fail();
        actual.close();
//        Attendance.DeleteAttendance(student_id, session_id);

    }

    @Test
    public void testDeleteAttendance() throws Exception {
        Attendance.CreateAttendance(student_id, session_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Attendance" +
                " WHERE student_id = " + student_id + " session_id = " + student_id + ";");
        if (actual.next()) fail();
        actual.close();
        Attendance.DeleteAttendance(student_id,session_id);
    }
}
