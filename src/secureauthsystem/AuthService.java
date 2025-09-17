
package secureauthsystem;


import java.util.regex.Pattern;

public class AuthService {

    public static String savedUsername = "Kyle";
    public static String savedPassword = "Smith";
    public static String savedFirstName = "Kyle";
    public static String savedLastName = "Smith";

    private static User registeredUser = null;

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9]{2,19}$");
    private static final Pattern PASSWORD_UPPER = Pattern.compile(".*[A-Z].*");
    private static final Pattern PASSWORD_LOWER = Pattern.compile(".*[a-z].*");
    private static final Pattern PASSWORD_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern PASSWORD_SPECIAL = Pattern.compile(".*[^A-Za-z0-9].*");
    private static final Pattern SA_CELL_PATTERN = Pattern.compile("^0(?:6|7|8)\\d{8}$");

    public enum LoginStatus {
        SUCCESS,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        INVALID_CELL,
        USER_ALREADY_EXISTS,
        REGISTER_SUCCESS,
        LOGIN_FAILED
    }

    public boolean checkUserName(String username) {
        if (username == null) return false;
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        if (password.length() < 8) return false;
        if (!PASSWORD_UPPER.matcher(password).matches()) return false;
        if (!PASSWORD_LOWER.matcher(password).matches()) return false;
        if (!PASSWORD_DIGIT.matcher(password).matches()) return false;
        if (!PASSWORD_SPECIAL.matcher(password).matches()) return false;
        return true;
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        String digitsOnly = cell.replaceAll("[^0-9]", "");
        return SA_CELL_PATTERN.matcher(digitsOnly).matches();
    }

    public LoginStatus registerUser(String username, String password, String firstName, String lastName, String cell) {
        if (!checkUserName(username)) return LoginStatus.INVALID_USERNAME;
        if (!checkPasswordComplexity(password)) return LoginStatus.INVALID_PASSWORD;
        if (!checkCellPhoneNumber(cell)) return LoginStatus.INVALID_CELL;

        if (registeredUser != null && registeredUser.getUsername().equalsIgnoreCase(username)) {
            return LoginStatus.USER_ALREADY_EXISTS;
        }

        String pwHash = simpleHash(password);
        registeredUser = new User(username, pwHash, firstName, lastName, cell);

        savedUsername = username;
        savedPassword = password;
        savedFirstName = firstName;
        savedLastName = lastName;

        return LoginStatus.REGISTER_SUCCESS;
    }

    public LoginStatus loginUser(String username, String password) {
        if (registeredUser != null) {
            if (registeredUser.getUsername().equals(username) && registeredUser.getPasswordHash().equals(simpleHash(password))) {
                return LoginStatus.SUCCESS;
            }
        }

        if (savedUsername != null && savedPassword != null) {
            if (savedUsername.equals(username) && savedPassword.equals(password)) {
                return LoginStatus.SUCCESS;
            }
        }

        return LoginStatus.LOGIN_FAILED;
    }

    public String returnLoginStatus(LoginStatus status) {
        switch (status) {
            case SUCCESS: return "Login successful.";
            case INVALID_USERNAME: return "Invalid username format. Must start with letter, 3-20 alphanumeric chars.";
            case INVALID_PASSWORD: return "Password does not meet complexity (min 8 chars, upper, lower, digit, special).";
            case INVALID_CELL: return "Invalid SA cellphone number. Starts with 06,07,08 and 10 digits.";
            case USER_ALREADY_EXISTS: return "User already exists.";
            case REGISTER_SUCCESS: return "Registration successful.";
            case LOGIN_FAILED:
            default: return "Login failed: incorrect username or password.";
        }
    }

    private String simpleHash(String input) {
        if (input == null) return null;
        int h = 7;
        for (char c : input.toCharArray()) {
            h = h*31 + c;
        }
        return Integer.toHexString(h);
    }

    public void clearRegisteredUser() {
        registeredUser = null;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }
}
