package com.example.project.data;

import android.util.Log;

import com.example.project.data.model.LoggedInUser;
import com.example.project.data.model.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    FireBaseDataBaseHandler fBH;
    public LoginDataSource(){
        fBH = new FireBaseDataBaseHandler();
        fBH.readUsersFromFireBase();
    }

    public Result<LoggedInUser> login(String username, String password) {
        Log.d("DBFB", "Login check");
        try {
            // TODO: handle loggedInUser authentication
            User u = new User();
            u.setUserName(username);
            u.setPassword(password);
            if(fBH.userNameAndPasswordMatches(u)) {
                User user = fBH.getUser(username);
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                user.getFirstName() + " " + user.getLastName() ,
                                user.getRole());
                Log.d("DBFB", "Login check user found");
                return new Result.Success<>(fakeUser);
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
        return new Result.Error(new IOException("Error logging in"));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}