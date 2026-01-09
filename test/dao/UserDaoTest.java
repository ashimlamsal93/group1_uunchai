/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {

    private static UserDao userDao;
    private static final String TEST_EMAIL = "testuser@unittest.com";
    private static final String TEST_PASSWORD = "test123";
    private static final String TEST_NAME = "Test User";
    private static final String TEST_PHONE = "9876543210";
    private static final String TEST_QUESTION = "What is your favorite place?";
    private static final String TEST_ANSWER = "Pokhara";

    @BeforeClass
    public static void setUpClass() {
        userDao = new UserDao();
        deleteTestUser();
    }

    @AfterClass
    public static void tearDownClass() {
        deleteTestUser();
    }

    @Before
    public void setUp() {
        deleteTestUser();
    }

    @After
    public void tearDown() {
        deleteTestUser();
    }

    // Reliable cleanup using direct SQL
    private static void deleteTestUser() {
        try {
            String sql = "DELETE FROM users WHERE email = ?";
            Connection conn = MySqlConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, TEST_EMAIL);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            // Ignore — user may not exist
        }
    }

    @Test
    public void testRegisterUser() {
        User user = createTestUser();
        boolean result = userDao.registerUser(user);
        assertTrue("User should be registered", result);
    }

    @Test
    public void testLoginUser() {
        User testUser = createTestUser();
        userDao.registerUser(testUser);

        User loggedIn = userDao.loginUser(TEST_EMAIL, TEST_PASSWORD);
        assertNotNull("Valid login should return user", loggedIn);
        assertEquals("Email should match", TEST_EMAIL, loggedIn.getEmail());

        User failed = userDao.loginUser(TEST_EMAIL, "wrong");
        assertNull("Wrong password should return null", failed);
    }

    @Test
    public void testEmailExists() {
        assertFalse("Email should not exist initially", userDao.emailExists(TEST_EMAIL));

        userDao.registerUser(createTestUser());

        assertTrue("Email should exist after registration", userDao.emailExists(TEST_EMAIL));
    }

    @Test
    public void testGetSecurityQuestion() {
        userDao.registerUser(createTestUser());

        String question = userDao.getSecurityQuestion(TEST_EMAIL);

        assertEquals("Security question should match", TEST_QUESTION, question);
    }

    @Test
    public void testVerifySecurityAnswer() {
        userDao.registerUser(createTestUser());

        assertTrue("Correct answer should verify", userDao.verifySecurityAnswer(TEST_EMAIL, TEST_QUESTION, TEST_ANSWER));
        assertFalse("Wrong answer should not verify", userDao.verifySecurityAnswer(TEST_EMAIL, TEST_QUESTION, "wrong"));
    }

    @Test
    public void testUpdatePassword() {
        userDao.registerUser(createTestUser());

        assertTrue("Password update should succeed", userDao.updatePassword(TEST_EMAIL, "newpass123"));

        assertNotNull("New password login should work", userDao.loginUser(TEST_EMAIL, "newpass123"));
        assertNull("Old password should not work", userDao.loginUser(TEST_EMAIL, TEST_PASSWORD));
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        userDao.registerUser(createTestUser());

        ResultSet rs = userDao.getAllUsers();
        assertNotNull("ResultSet should not be null", rs);

        boolean found = false;
        while (rs.next()) {
            if (TEST_EMAIL.equals(rs.getString("email"))) {
                found = true;
                break;
            }
        }
        assertTrue("Test user should be in list", found);
        rs.close();
    }

    private User createTestUser() {
        User user = new User();
        user.setName(TEST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setPhone(TEST_PHONE);
        user.setRole("USER");
        user.setSecurityQuestion(TEST_QUESTION);
        user.setSecurityAnswer(TEST_ANSWER);
        return user;
    }
}