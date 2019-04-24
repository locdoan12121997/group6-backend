package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class SemesterTest {


    public SemesterTest() throws SQLException {
    }

    static final String DB_URL = "jdbc:mysql://localhost:3306/register_db";
    static final String USER = "root";
    static final String PASS = "loc123";
    private Statement stm;
    private ResultSet rs;
    private String sql;
    private SemesterController ctrl;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        stm = connection.createStatement();
        sql = "CALL CreateSemester(2015-01-01, 2016-02-02);";
        rs = stm.executeQuery(sql);
        ctrl = new SemesterController();
    }

    @After
    public void tearDown() throws Exception {
        rs.close();
        stm.close();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test(expected=NullPointerException.class)
    public void testCreateSemester() throws SQLException {
        ctrl.createSemester("2018-03-04", "2019-08-03");
        sql = "SELECT fromTime, toTime FROM Semester WHERE fromTime="+ "2018-03-04" +" AND toTiem="+"2019-08-03" +";";
        rs = stm.executeQuery(sql);
        assertNotNull(rs);
    }


    @Test(expected=NullPointerException.class)
    public void testDeleteSemester() throws SQLException {
        sql = "SELECT LAST_INSERT_ID();";
        rs = stm.executeQuery(sql);
        int last_id = rs.getInt("id");
        ctrl.deleteSemesters(last_id);
        sql = "SELECT fromTime, toTime FROM Semester WHERE fromTime="+ "2018-03-04" +" AND toTiem="+"2019-08-03" +";";
        rs = stm.executeQuery(sql);
        assertNull(rs);
    }


    @Test(expected=NullPointerException.class)
    public void testUpdateSemester() throws SQLException {
        sql = "SELECT LAST_INSERT_ID();";
        rs = stm.executeQuery(sql);
        int last_id = rs.getInt("id");
        ctrl.updateSemester(last_id, "2015-08-08", "2017-04-04");
        sql = "SELECT fromTime, toTime FROM Semester WHERE fromTime="+ "2015-08-08" +" AND toTiem="+"2017-04-04" +";";
        rs = stm.executeQuery(sql);
        assertNull(rs);
        assertEquals("2015-08-08", rs.getDate("fromTime").toString());
        assertEquals("2017-04-04", rs.getDate("toTime").toString());
    }
}
