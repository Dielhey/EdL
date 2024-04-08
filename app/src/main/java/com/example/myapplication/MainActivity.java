
package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends EdLActivity {

    // Data
    User userChosen;
    private DatabaseClient mDb;
    private MainAdapter adapter;

    // Views
    ListView lvLogin;
    Button btnGuest, btnSignUp;

    @Override
    protected void onStart() {
        super.onStart();

        getUsers();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        adapter = new MainAdapter(this, new ArrayList<>());
        btnGuest = findViewById(R.id.btnGuest);
        btnSignUp = findViewById(R.id.btnSignUp);
        lvLogin = findViewById(R.id.lvLogin);
        lvLogin.setAdapter(adapter);

        // Bouton dans l'adapter pour un clic plus joli
//        lvLogin.setOnItemClickListener((parent, view, position, id) -> {
//            userChosen = adapter.getItem(position);
//            setLoginId(userChosen.getId());
//            long loginId = getLoginId();
//            User userFound = new User();
//            getUserBy(loginId, userFound);
//        });

        btnGuest.setOnClickListener(v -> {
            setLoginId(-1);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);

                adapter.clear();
                adapter.addAll(users);

                adapter.notifyDataSetChanged();
            }
        }

        GetUsers gu = new GetUsers();
        gu.execute();
    }

    private void getUserBy(long id, User userFound) {
        class GetUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = mDb.getAppDatabase()
                        .userDao()
                        .getById(id);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                userFound.setId(user.getId());
                userFound.setNom(user.getNom());
                userFound.setPrenom(user.getPrenom());
            }
        }

        GetUser gu = new GetUser();
        gu.execute();
    }


}