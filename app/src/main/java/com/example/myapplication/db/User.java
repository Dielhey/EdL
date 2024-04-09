package com.example.myapplication.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

/**
 * Classe pour la base de données d'un utilisateur.
 * Contient son nom, son prénom, un id auto-incrémenté, et une méthode toString pour un affichage clair de l'utilisateur.
 */
@Entity (tableName = "users")
public class User {

        @PrimaryKey(autoGenerate = true)
        private long id;
        private String prenom;
        private String nom;

        public long getId() {
                return id;
        }
        public String getPrenom() {return prenom;}
        public String getNom() {
                return nom;
        }
        public String toString() {return prenom + " " + nom;}

        public void setPrenom(String prenom) {
                this.prenom = toCapital(prenom);
        }
        public void setNom(String nom) {
                this.nom = nom.toUpperCase();
        }
        public void setId(long id) {
                this.id = id;
        }

        // Permet d'avoir un joli affichage du prénom
        private String toCapital(String text) {
                return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
        }
}
