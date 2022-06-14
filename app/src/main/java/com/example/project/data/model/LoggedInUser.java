package com.example.project.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String userRole;

    public LoggedInUser(String userId, String displayName, String role) {
        this.userId = userId;
        this.displayName = displayName;
        this.userRole = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getUserRole(){return userRole;}
}