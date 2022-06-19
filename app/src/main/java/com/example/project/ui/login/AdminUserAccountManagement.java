package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.User;

public class AdminUserAccountManagement extends AppCompatActivity {
    public boolean isUserNameValid ;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readUsersFromFireBase();
        ArrayAdapter<User> adapter;
        setContentView(R.layout.activity_admin_user_account_management);

        final EditText userName = (EditText) findViewById(R.id.UserToBeDeleted);
        final Button userDeleteBtn = (Button) findViewById(R.id.deleteUserBtn);
        final Button backToMenu = findViewById(R.id.a_e_returnToMenu);
        final ListView listView = findViewById(R.id.deleteUserListView);



        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                displayUsers();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Patterns.EMAIL_ADDRESS.matcher(userName.getText().toString()).matches()){
                    userName.setError(null);
                    isUserNameValid = true;
                }
                else{
                    userName.setError("Must contain @");
                }
                if(enableUserDeleteButton()){
                    userDeleteBtn.setEnabled(true);
                }
            }

        });



        userDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setUserName(userName.getText().toString());
                if(fBH.userExistsInDatabase(u)){
                    Log.d("DBFB", "user exist");
                   // Toast.makeText(getApplicationContext(), "User " + u.getUserName() + "  found !!" , Toast.LENGTH_SHORT).show();
                    fBH.deleteUser(u);
                    Toast.makeText(getApplicationContext(), "The " + u.getUserName() + " found and has been deleted" , Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("DBFB", "User with"+u.getUserName()+" do not exist ");
                    Toast.makeText(getApplicationContext(), "User " + u.getUserName() + "  do not exist in the database" , Toast.LENGTH_SHORT).show();
                }
                displayUsers();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminUserAccountManagement.this,AdminActivity.class);
                startActivity(backToAdminActivity);
            }
        });

        displayUsers();


    }

    private void displayUsers() {

            Log.d("DBFB", "displayUsers inside if");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fBH.getUserNames());
            final ListView listView = findViewById(R.id.deleteUserListView);
            listView.setAdapter(adapter);



    }


    private boolean enableUserDeleteButton(){
        if(isUserNameValid){
            return true;
        }
        else{
            return false;
        }
    }
}