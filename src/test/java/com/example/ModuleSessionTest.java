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
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:00", "12:00:00", moduleId);
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM  ModuleSession WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("1111-11-11", actual.getString("date_of_session"));
            assertEquals("08:45:00", actual.getString("from_time"));
            assertEquals("12:00:00", actual.getString("to_time"));
            assertEquals(moduleId, actual.getInt("module_id"));
        } else fail();
        actual.close();
        ModuleSession.DeleteModuleSession(last_id);
    }

    @Test
    public void DeleteModuleSession() throws Exception{
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:00", "12:00:00", moduleId);
        int last_id = Main.LastInsertId();
        ModuleSession.DeleteModuleSession(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM  ModuleSession WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }

    @Test
    public void UpdateModuleSession() throws Exception{
        ModuleSession.CreateModuleSession("1111-11-11", "08:45:00", "12:00:00", moduleId);
        int last_id = Main.LastInsertId();
        ModuleSession.UpdateModuleSession(last_id, "1212-12-12", "09:46:01", "13:01:01");
        ResultSet actual = Main.getResultSet("SELECT * FROM  ModuleSession WHERE id = " + last_id);
        if (actual.next()) {
            assertEquals("1212-12-12", actual.getString("date_of_session"));
            assertEquals("09:46:01", actual.getString("from_time"));
            assertEquals("13:01:01", actual.getString("to_time"));
            assertEquals(moduleId, actual.getInt("module_id"));
        } else fail();
        ModuleSession.DeleteModuleSession(last_id);
        actual.close();
    }
}
