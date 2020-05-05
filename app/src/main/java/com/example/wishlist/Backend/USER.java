package com.example.wishlist.Backend;

import android.database.Cursor;

import com.example.wishlist.DAO.UserDAO;

public class USER {
    public String Pseudo;
    public String MDP;
    UserProfile userprofile;
    public UserDAO dao;
    private String nom;
    private String prenom;
    private String sexe;
    private byte[] photo;
    private String langue;


    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNom(){
        return this.nom;
    }

    public void setPseudo(String pseudo){
        this.Pseudo=pseudo;
    }

    public String getPseudo(){
        return this.Pseudo;
    }

    public void setMDP(String mdp){
        this.MDP=mdp;
    }

    public String getMDP(){
        return this.MDP;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }


    public String getSexe(){
        return this.sexe;
    }

    public void setSexe(String sexe){
        this.sexe=sexe;
    }

    public String getLangue(){
        return this.langue;
    }

    public void setLangue(String langue){
        this.langue=langue;
    }
}
