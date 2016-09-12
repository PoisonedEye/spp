package services;

import org.junit.Test;
import stubs.SessionStub;
import util.HibernateUtil;

import java.util.HashMap;

import static org.junit.Assert.*;

public class LoginServiceTest {

    @Test
    public void testIsLogined_emptySession_returnFalse() throws Exception {
        // Arrange
        LoginService ls = new LoginService();

        // Act
        boolean result = ls.isLogined(new HashMap<>());

        // Assert
        if (result) fail();
    }

    @Test
    public void testIsLogined_nullSession_returnFalse() throws Exception {
        // Arrange
        LoginService ls = new LoginService();

        // Act
        boolean result = ls.isLogined(null);

        // Assert
        if (result) fail();
    }

    @Test
    public void testIsLogined_trueValue_returnTrue() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        map.put("logined",true);

        // Act
        boolean result = ls.isLogined(map);

        // Assert
        if (!result) fail();
    }

    @Test
    public void testIsLogined_falseValue_returnFalse() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        map.put("logined",false);

        // Act
        boolean result = ls.isLogined(map);

        // Assert
        if (result) fail();
    }

    @Test
    public void testLogout_nullSession_SessionIsNullString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();

        // Act
        String result = ls.Logout(null);

        // Assert
        assertEquals(result,"Session is null.");
    }

    @Test
    public void testLogout_emptySession_NotLoginedString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();

        // Act
        String result = ls.Logout(map);

        // Assert
        assertEquals(result,"Not logined.");
    }

    @Test
    public void testLogout_falseValue_NotLoginedString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        map.put("logined",false);

        // Act
        String result = ls.Logout(map);

        // Assert
        assertEquals(result,"Not logined.");
    }

    @Test
    public void testLogout_trueValue_TrueString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        map.put("logined",true);

        // Act
        String result = ls.Logout(map);

        // Assert
        assertEquals(result,"true");
    }

    @Test
    public void testLogin_nullSession_NullSessionString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HibernateUtil.testing = true;
        SessionStub.mode = "loginTesting";

        // Act
        String result = ls.Login("login","password",null);

        // Assert
        assertEquals(result,"Session is null.");
    }

    @Test
    public void testLogin_sessionWithTrueLoginValue_LoginIsAlreadyExecutedString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        HibernateUtil.testing = true;
        map.put("logined",true);
        SessionStub.mode = "loginTesting";

        // Act
        String result = ls.Login("login","password",map);

        // Assert
        assertEquals(result,"Login is already executed.");
    }

    @Test
    public void testLogin_invalidLogin_IncorrectUsernameOrPasswordString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        HibernateUtil.testing = true;
        SessionStub.mode = "loginTesting";

        // Act
        String result = ls.Login("loginnn","password",map);

        // Assert
        assertEquals(result,"true");
    }

    @Test
    public void testLogin_invalidPassword_IncorrectUsernameOrPasswordString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        HashMap<String,Object> map = new HashMap<>();
        HibernateUtil.testing = true;
        SessionStub.mode = "loginTesting";
        String login = "login";

        // Act
        String result = ls.Login(login,"passwordddd",map);

        // Assert
        assertEquals(result,"Incorrect username or password");
    }

    @Test
    public void testLogin_validLoginAndPassword_TrueString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        SessionStub.mode = "loginTesting";
        HashMap<String,Object> map = new HashMap<>();
        HibernateUtil.testing = true;
        String login = "login";
        String password = "password";

        // Act
        String result = ls.Login(login,password,map);

        // Assert
        assertEquals(result,"true");
    }

    @Test
    public void testLogin_validLoginAndPasswordWithFalseValue_TrueString() throws Exception {
        // Arrange
        LoginService ls = new LoginService();
        SessionStub.mode = "loginTesting";
        HashMap<String,Object> map = new HashMap<>();
        map.put("login",false);
        HibernateUtil.testing = true;
        String login = "login";
        String password = "password";

        // Act
        String result = ls.Login(login,password,map);

        // Assert
        assertEquals(result,"true");
    }
}