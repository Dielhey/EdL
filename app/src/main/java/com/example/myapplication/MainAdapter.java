package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.User;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

/**
 * Un adapter pour les utilisateurs
 */
public class MainAdapter extends ArrayAdapter<User> {

    private SharedPreferences prefs;
    public MainAdapter(Context context, List<User> usersList) {
        super(context, R.layout.activity_main, usersList);
        prefs = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final User user = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_main, parent, false);

        TextView tvUser = rowView.findViewById(R.id.tvUser);
        tvUser.setText(user.toString());

        MaterialCardView cardLogin = rowView.findViewById(R.id.cardLogin);
        // Affichage plus joli du clic avec l'événement ici
        cardLogin.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong(getContext().getString(R.string.login_id_key), user.getId());
            editor.apply();
            Toast.makeText(getContext(), "Connecté en tant que " + user + " !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), HomeActivity.class);
            getContext().startActivity(intent);
        });


        return rowView;
    }
}
