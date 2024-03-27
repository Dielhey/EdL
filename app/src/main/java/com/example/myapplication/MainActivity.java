package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Maths.MathsActivity;
import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public final static int EXERCICE_5_ACTIVITY_REQUEST = 4;
    public final static int ADD_USER_ACTIVITY_REQUEST = 5;
    public final static int LIST_USER_ACTIVITY_REQUEST = 6;
    private DatabaseClient mDb;
    private Intent mathsIntent, userAddIntent, listUsersIntent;

    @Override
    protected void onStart() {
        super.onStart();
        mathsIntent = new Intent(this, MathsActivity.class);
        userAddIntent = new Intent(this, AddUserActivity.class);
        listUsersIntent = new Intent(this, ListUsersActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = DatabaseClient.getInstance(getApplicationContext());

    }

    public void onExercice5(View view) {

        // Création d'une intention
        Intent intent = new Intent(this, MathsActivity.class);

        // Lancement de la demande de changement d'activité avec attente de résultat par la méthode onActivityResult
        startActivityForResult(intent, EXERCICE_5_ACTIVITY_REQUEST);
    }

    public void onAddition(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '+');
        startActivity(mathsIntent);
    }

    public void onMultiplication(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '*');
        startActivity(mathsIntent);
    }
    public void onSubstraction(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '-');
        startActivity(mathsIntent);
    }

    public void onAddUser(View view) {
        startActivity(userAddIntent);
    }

    public void onListUsers(View view) {
        startActivity(listUsersIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Vérification du retour à l'aide du code requête
       if (requestCode == EXERCICE_5_ACTIVITY_REQUEST) {

            // Afficher une notification
            String notification = "Retour exercice 5";
            Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();
        }
    }

    public void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {
            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase().userDao().getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
            }


        }
        GetUsers gu = new GetUsers();
        gu.execute();
    }
}