package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.*;

import static org.junit.Assert.*;

public class AssistantTest {

    @Before
    public void setUp() throws Exception {
        Main.establishConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAssistant() throws Exception {
        Assistant.CreateAssistantAccount("username", "password", "firstname", "lastname");
        int last_id = Main.LastInsertId();
        ResultSet actual = Main.getResultSet("SELECT * FROM Assistant JOIN Account ON Assistant.account_id = Account.id WHERE Assistant.id = " + last_id);
        if (actual.next()) {
            String[] expectedStrings = {"username", "password", "firstname", "lastname"};
            String[] actualStrings = {actual.getString("user_name"), actual.getString("user_password"), actual.getString("first_name"), actual.getString("last_name")};
            assertArrayEquals(expectedStrings, actualStrings);
        }
        else fail();
        actual.close();
        Assistant.DeleteAssistantAccount(last_id);

    }

    @Test
    public void testVerifyAssistant() throws Exception {
        Assistant.CreateAssistantAccount("username", "password", "firstname", "lastname");
        int last_id = Main.LastInsertId();
        assertFalse(!Assistant.VerifyAssistantAccount("username", "password"));
        assertFalse(Assistant.VerifyAssistantAccount("wrongusername", "wrongpassword"));
        Assistant.DeleteAssistantAccount(last_id);
    }

    @Test
    public void testDeleteAssistant() throws Exception {
        Assistant.CreateAssistantAccount("username", "password", "firstname", "lastname");
        int last_id = Main.LastInsertId();
        Assistant.DeleteAssistantAccount(last_id);
        ResultSet actual = Main.getResultSet("SELECT * FROM Assistant WHERE id = " + last_id);
        if (actual.next()) fail();
        actual.close();
    }
}
