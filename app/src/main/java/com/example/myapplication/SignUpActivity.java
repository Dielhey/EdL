package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

public class SignUpActivity extends EdLActivity {


    private String prenom, nom;
     private DatabaseClient mDb;
     private EditText inputPrenom, inputNom;
     private Button btnAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        inputNom = findViewById(R.id.inputNom);
        inputPrenom = findViewById(R.id.inputPrenom);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(view -> {
            prenom = inputPrenom.getText().toString();
            nom = inputNom.getText().toString();
            saveUser();
        });
    }

    public void saveUser() {
        class SaveUser extends AsyncTask<Void, Void, User> {
            @Override
            protected User doInBackground(Void... voids) {
                if(nom.equals("") && prenom.equals("")) {
                    return null;
                }
                User user = new User();

                user.setNom(nom);
                user.setPrenom(prenom);

                long id = mDb.getAppDatabase().userDao().insert(user);
                user.setId(id);
                return user;
            }
            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if (user == null) {
                    Toast.makeText(SignUpActivity.this, "Vous devez écrire un nom et prénom !", Toast.LENGTH_SHORT).show();
                    return;
                }
                setLoginId(user.getId());
                Toast.makeText(getApplicationContext(), "Ajout de l'utilisateur " + user, Toast.LENGTH_LONG).show();
                signUp();
            }
        }
        SaveUser su = new SaveUser();
        su.execute();
    }
    public void signUp() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}