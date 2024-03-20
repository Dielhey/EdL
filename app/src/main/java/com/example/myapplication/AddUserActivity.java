package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

public class AddUserActivity extends AppCompatActivity {


     private DatabaseClient mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        mDb = DatabaseClient.getInstance(getApplicationContext());
    }

    public void saveUser() {
        class SaveUser extends AsyncTask<Void, Void, User> {
            @Override
            protected User doInBackground(Void... voids) {
                User user = new User();
                user.setNom("testNom");
                user.setPrenom("testPrenom");

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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveUser su = new SaveUser();
        su.execute();
    }
}