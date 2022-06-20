package com.example.project.data.model;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;


    private String role;


    public User(String userName, String firstName, String lastName, String password, String role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }
    public User(){

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Role getRole() {
//        return role;
//    }
//    public String getRoleAsString(){
//        switch (this.role){
//            case STUDENT:
//                return "STUDENT";
//            case INSTRUCTOR:
//                return "INSTRUCTOR";
//            case ADMIN:
//                return "ADMIN";
//        }
//        return "";
//    }

 //   public void setRole(Role role) {
 //       this.role = role;
 //   }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
//    public static Role convertToRole(String value){
//        switch (value){
//            case "STUDENT":
//                return Role.STUDENT;
//            case "INSTRUCTOR":
//                return Role.INSTRUCTOR;
//            case "ADMIN":
//                return Role.ADMIN;
//
//        }
//        return null;
//    }
}
