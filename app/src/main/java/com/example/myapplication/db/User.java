package com.example.myapplication.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
public class User {

        @PrimaryKey(autoGenerate = true)
        private long id;
        private String prenom;
        private String nom;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getPrenom() {return prenom;}

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String getNom() {
                return nom;
        }

        public void setPrenom(String prenom) {
                this.prenom = prenom;
        }
}
