package model;

public class UserSession {
    private static UserSession instance;
    private int userId;
    private String userEmail;
    private boolean loggedIn;
    
    private UserSession() {
        loggedIn = false;
    }
    
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    
    public void login(int userId, String userEmail) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.loggedIn = true;
        System.out.println("User logged in: " + userEmail);
    }
    
    public void logout() {
        System.out.println("User logged out: " + userEmail);
        this.userId = 0;
        this.userEmail = null;
        this.loggedIn = false;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
}