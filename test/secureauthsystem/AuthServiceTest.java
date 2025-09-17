
package secureauthsystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthServiceTest {

    private AuthService auth;

    @Before
    public void setUp() {
        auth = new AuthService();
        auth.clearRegisteredUser();
        AuthService.savedUsername = "Kyle";
        AuthService.savedPassword = "Smith";
        AuthService.savedFirstName = "Kyle";
        AuthService.savedLastName = "Smith";
    }

    @Test
    public void testValidUsernames() {
        assertTrue(auth.checkUserName("Alex123"));
        assertTrue(auth.checkUserName("a1b2c3"));
        assertTrue(auth.checkUserName("Abc"));
    }

    @Test
    public void testInvalidUsernames() {
        assertFalse(auth.checkUserName(null));
        assertFalse(auth.checkUserName("ab"));
        assertFalse(auth.checkUserName("1abc"));
        assertFalse(auth.checkUserName("A_this_has_symbol"));
    }

    @Test
    public void testPasswordComplexityValid() {
        assertTrue(auth.checkPasswordComplexity("Aa1!aaaa"));
        assertTrue(auth.checkPasswordComplexity("StrongP@ssw0rd"));
    }

    @Test
    public void testPasswordComplexityInvalid() {
        assertFalse(auth.checkPasswordComplexity(null));
        assertFalse(auth.checkPasswordComplexity("short1!"));
        assertFalse(auth.checkPasswordComplexity("nocaps1!"));
    }

    @Test
    public void testValidSAcell() {
        assertTrue(auth.checkCellPhoneNumber("0712345678"));
        assertTrue(auth.checkCellPhoneNumber("0821234567"));
        assertTrue(auth.checkCellPhoneNumber("071-234-5678"));
    }

    @Test
    public void testInvalidSAcell() {
        assertFalse(auth.checkCellPhoneNumber(null));
        assertFalse(auth.checkCellPhoneNumber("12345"));
        assertFalse(auth.checkCellPhoneNumber("0912345678"));
    }

    @Test
    public void testRegisterAndLoginSuccess() {
        AuthService.LoginStatus reg = auth.registerUser("Peter1", "Abcde1!@", "Peter", "Parker", "0712345678");
        assertEquals(AuthService.LoginStatus.REGISTER_SUCCESS, reg);

        AuthService.LoginStatus login = auth.loginUser("Peter1", "Abcde1!@");
        assertEquals(AuthService.LoginStatus.SUCCESS, login);
    }

    @Test
    public void testLoginFallbackToSavedStatic() {
        AuthService.LoginStatus login = auth.loginUser("Kyle", "Smith");
        assertEquals(AuthService.LoginStatus.SUCCESS, login);
    }

    @Test
    public void testLoginFailsWrongCredentials() {
        AuthService.LoginStatus login = auth.loginUser("noone", "badpass");
        assertEquals(AuthService.LoginStatus.LOGIN_FAILED, login);
    }
}
