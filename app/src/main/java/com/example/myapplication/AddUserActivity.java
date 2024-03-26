package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

public class AddUserActivity extends AppCompatActivity {


    private String prenom, nom;
     private DatabaseClient mDb;
     private EditText inputPrenom, inputNom;
     private Button btnAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
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
                    try {
                        throw new Exception("Vous devez écrire un nom et prénom !");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved user " + String.valueOf(user.getId()), Toast.LENGTH_LONG).show();
            }
        }
        SaveUser su = new SaveUser();
        su.execute();

    }
}