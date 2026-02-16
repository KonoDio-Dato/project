package inter;

public class UserSession {
    private static UserSession instance;
    private String fullName;
    private String age;
    private String address;
    private String email;
    private String phoneNumber;
    private String studentId;
    private String username;
    private String password;
    private String gender;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserDetails(String fullName, String age, String address, String email, String phoneNumber, String studentId, String username, String password, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    // Add other getters as needed
}