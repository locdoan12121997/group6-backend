package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class ModuleTest {

    private int semesterId;

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        semesterId = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        Semester.DeleteSemester(semesterId);
    }
    @Test
    public void testCreateModule() throws Exception {
        Module.CreateModule("code", "name", semesterId);
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM  Module WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("code", actual.getString("code"));
            assertEquals("name", actual.getString("name"));
            assertEquals(semesterId, actual.getInt("semester_id"));
        }
        else fail();
        actual.close();
        Module.DeleteModule(last_id);
    }

    @Test
    public void testUpdateSemester() throws Exception {
        Module.CreateModule("code", "name", semesterId);
        int last_id = Main.LastInsertId();
        Module.UpdateModule(last_id, "code2", "name2");
        ResultSet actual = Main.getResultSet("SELECT * FROM  Module WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("code2", actual.getString("code"));
            assertEquals("name2", actual.getString("name"));
            assertEquals(semesterId, actual.getInt("semester_id"));
        }
        else fail();
        Module.DeleteModule(last_id);
    }

    @Test
    public void testDeleteModule() throws Exception {
        Module.CreateModule("code", "name", semesterId);
        int last_id = Main.LastInsertId();
        Module.DeleteModule(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM  Module WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
        Module.DeleteModule(last_id);
    }

    @Test
    public void testEnrollStudent() throws Exception{
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "studentcode");
        int last_student_id = Main.LastInsertId();
        Module.CreateModule("code", "name", semesterId);
        int last_module_id = Main.LastInsertId();
        Module.EnrollStudent(last_student_id, last_module_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM ModuleList" +
                " WHERE student_id = " + last_student_id +
                " AND module_id = " + last_module_id);
        if (actual.next()){
            assertEquals(last_student_id, actual.getInt("student_id"));
            assertEquals(last_module_id, actual.getInt("module_id"));
        }
        else fail();
        actual.close();
        Module.UnenrollStudent(last_student_id, last_module_id);
        Student.DeleteStudentAccount(last_student_id);
        Module.DeleteModule(last_module_id);
    }

    @Test
    public void testUnenrollStudent() throws Exception{
        Student.CreateStudentAccount("username", "password", "firstname", "lastname", "studentcode");
        int last_student_id = Main.LastInsertId();
        Module.CreateModule("code", "name", semesterId);
        int last_module_id = Main.LastInsertId();
        Module.EnrollStudent(last_student_id, last_module_id);
        Module.UnenrollStudent(last_student_id, last_module_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM ModuleList" +
                " WHERE student_id = " + last_student_id +
                " AND module_id = " + last_module_id);
        if (actual.next()) fail();
        actual.close();
        Student.DeleteStudentAccount(last_student_id);
        Module.DeleteModule(last_module_id);
    }
}
