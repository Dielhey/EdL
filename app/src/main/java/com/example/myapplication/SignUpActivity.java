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


    // Data
    private String prenom, nom;
     private DatabaseClient mDb;

     // Views
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
        // Confirmation du formulaire
        btnAddUser.setOnClickListener(view -> {
            prenom = inputPrenom.getText().toString();
            nom = inputNom.getText().toString();
            saveUser();
        });
    }

    public void saveUser() {
        class SaveUser extends AsyncTask<Void, Void, User> {
            // Gère les erreurs de formulaire et rajoute l'utilisateur
            @Override
            protected User doInBackground(Void... voids) {


                if(nom.equals("") && prenom.equals("")) {
                    return null;
                }
                User user = new User();

                user.setNom(nom);
                user.setPrenom(prenom);

                User duplicate = mDb.getAppDatabase().userDao().getDuplicate(user.getNom(), user.getPrenom());
                if (duplicate != null) {
                    return null;
                }

                long id = mDb.getAppDatabase().userDao().insert(user);
                user.setId(id);
                return user;
            }

            // Gestion des erreurs et bon affichage d=sur l'application selon le retour
            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if(nom.equals("") && prenom.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Vous devez écrire un nom et prénom !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user == null) {
                    Toast.makeText(SignUpActivity.this, "Cet utilisateur existe déjà !", Toast.LENGTH_SHORT).show();
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