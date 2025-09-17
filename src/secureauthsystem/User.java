

package secureauthsystem;
/**
 * Simple User model.
 */
public class User {
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;

    public User(String username, String passwordHash, String firstName, String lastName, String cellPhoneNumber) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}
