package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;


/**
 * Activité général, gère les préférences et la barre de navigation
 */
public class EdLActivity extends AppCompatActivity {

    private DatabaseClient mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDb = DatabaseClient.getInstance(getApplicationContext());

        refreshLogin();
        goHome();
    }

    protected SharedPreferences getSharedPreferences() {
        return getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    protected long getLoginId() {
        SharedPreferences prefs = getSharedPreferences();
        return prefs.getLong(getString(R.string.login_id_key), -1);
    }

    protected void setLoginId(long id) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(getString(R.string.login_id_key), id);
        editor.apply();
    }

    // Affiche le nom dans la barre de navigation
    private void refreshLogin() {
        TextView loginView = findViewById(R.id.login);

        if (loginView == null) {
            return;
        }
        long id = getLoginId();
        // Le login est automatiquement mis à -1 pour les invités
        if(id == -1) {
            loginView.setText("Invité");
            return;
        }
        User user = new User();
        getLogin(getLoginId(), user, loginView);

    }

    // Permet de revenir à l'accueil
    public void goHome() {
        TextView tvHome = findViewById(R.id.tvGoHome);
        if (tvHome == null) {
            return;
        }
        tvHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
    }

    // Obtient l'utilisateur connecté
    private void getLogin(long id, User userFound, TextView loginView) {
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
                loginView.setText(user.getPrenom());
            }
        }

        GetUser gu = new GetUser();
        gu.execute();
    }

}