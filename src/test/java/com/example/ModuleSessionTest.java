package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class ModuleSessionTest {
    int semesterId, moduleId;
    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
        Semester.CreateSemester("1111-11-11", "1212-12-12");
        semesterId = Main.LastInsertId();
        Module.CreateModule("code", "name", semesterId);
        moduleId = Main.LastInsertId();
    }

    @After
    public void tearDown() throws Exception {
        Module.DeleteModule(moduleId);
        Semester.DeleteSemester(semesterId);
    }

    @Test
    public void CreateModuleSession() throws Exception{
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:12", "12:00:12", moduleId);
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM  ModuleSession WHERE id = " + last_id);
        if (actual.next()){
            System.out.println(actual.getDate("date_of_session"));
            System.out.println(actual.getTime("from_time"));
            System.out.println(actual.getTime("to_time"));
            System.out.println(actual.getInt("module_id"));
            assertEquals("1111-11-11", actual.getDate("date_of_session").toString());
            assertEquals("09:45:12", actual.getTime("from_time").toString());
            assertEquals("13:00:12", actual.getTime("to_time").toString());
            assertEquals(moduleId, actual.getInt("module_id"));
        }
        else fail();
        ModuleSession.DeleteModuleSession(last_id);
    }
}
