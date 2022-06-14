package com.example.project.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String userRole;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String role) {
        this.displayName = displayName;
        this.userRole = role;
    }

    String getDisplayName() {
        return displayName;
    }
    String getUserRole(){
        return userRole;
    }
}