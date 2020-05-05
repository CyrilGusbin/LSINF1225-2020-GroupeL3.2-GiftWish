package com.example.wishlist.Backend;

import android.provider.BaseColumns;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class FeedReaderContract {
    public FeedReaderContract(){}
    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_AUTORISATION="Autorisation";
        public static final String COLUMN_AUTORISATION_PSEUDO="PSEUDO";
        public static final String COLUMN_AUTORISATION_IDWL="ID_WL";
        public static final String COLUMN_AUTORISATION_EDIT="Edit";
        public static final String TABLE_FRIEND="FRIEND";
        public static final String COLUMN_FRIEND_PSEUDO="PSEUDO";
        public static final String COLUMN_FRIEND_PSEUDO2="PSEUDO_2";
        public static final Boolean COLUMN_FRIEND_FRIEND = false;
        public static final String TABLE_ITEM ="ITEM";
        public static final String COLUMN_ITEM_ID="ID";
        public static final String COLUMN_ITEM_WL="WL";
        public static final String COLUMN_ITEM_NOM="Nom";
        public static final String COLUMN_ITEM_DESCRIPTION="Description";
        public static final String COLUMN_ITEM_PRIX="Prix";
        public static final String COLUMN_ITEM_ETAT="etat";
        public static final String COLUMN_ITEM_IDWL="ID_WL";
        public static final String TABLE_PROFIL="PROFIL";
        public static final String COLUMN_PROFIL_PSEUDO="Pseudo";
        public static final String COLUMN_PROFIL_NOM="Nom";
        public static final String COLUMN_PROFIL_PRENOM="Prénom";
        public static final String COLUMN_PROFIL_AGE="Age";
        public static final String COLUMN_PROFIL_ADRESSE="Adresse";
        public static final String COLUMN_PROFIL_PHOTO="Photo";
        public static final String TABLE_PREFERENCES="Preferences";
        public static final String COLUMN_PREFERENCES_PSEUDO="Pseudo";
        public static final String COLUMN_PREFERENCES_TAILLE_P="TailleP";
        public static final String COLUMN_PREFERENCES_TAILLE_H="TailleH";
        public static final String COLUMN_PREFERENCES_POINTURE="Pointure";
        public static final String COLUMN_PREFERENCES_COULEUR="Couleur";
        public static final String COLUMN_PREFERENCES_CENTREI="CentreI";
        public static final String TABLE_USER="USER";
        public static final String COLUMN_USER_PSEUDO="Pseudo";
        public static final String COLUMN_USER_MDP="MDP";
        public static final String TABLE_WL="WL";
        public static final String COLUMN_WL_PSEUDO="Pseudo";
        public static final String COLUMN_WL_NWL="NWL";
        public static final String COLUMN_WL_DESCRIPTION="Description";
        public static final String COLUMN_WL_EDIT="EDIT";
        public static final String COLUMN_WL_IDWL="ID_WL";

    }
    public static final String SQL_CREATE_ENTRIES="CREATE TABLE " + FeedEntry.TABLE_AUTORISATION +"(" +
    FeedEntry.COLUMN_AUTORISATION_PSEUDO + " varchar (20) NOT NULL REFERENCES USER (Pseudo)," +
    FeedEntry.COLUMN_AUTORISATION_IDWL + " VARCHAR (20) NOT NULL REFERENCES WL (ID_WL)," +
    FeedEntry.COLUMN_AUTORISATION_EDIT + " bit NOT NULL" + ")";
    public static final String SQL_CREATE_ENTRIES2="CREATE TABLE " + FeedEntry.TABLE_FRIEND + "(" +
    FeedEntry.COLUMN_FRIEND_PSEUDO + " varchar(20) NOT NULL," +
    FeedEntry.COLUMN_FRIEND_PSEUDO2 + " varchar(20) NOT NULL," +
    FeedEntry.COLUMN_FRIEND_FRIEND + " bit NOT NULL" +")";
    public static final String SQL_CREATE_ENTRIES7="CREATE TABLE " +  FeedEntry.TABLE_PROFIL  + "("+
            FeedEntry.COLUMN_PROFIL_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_PROFIL_NOM + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_PRENOM + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_AGE + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_ADRESSE +" VARCHAR (100)," +
            FeedEntry.COLUMN_PROFIL_PHOTO +" LONGBLOB" +")";
    public static final String SQL_CREATE_ENTRIES3="CREATE TABLE " +  FeedEntry.TABLE_ITEM + "(" +
            FeedEntry.COLUMN_ITEM_ID + " varchar (20) PRIMARY KEY NOT NULL," +
            FeedEntry.COLUMN_ITEM_WL +  " varchar (20) NOT NULL,"+
            FeedEntry.COLUMN_ITEM_NOM + " varchar (20) NOT NULL,"+
            FeedEntry.COLUMN_ITEM_DESCRIPTION +  " varchar (20) NOT NULL,"+
            FeedEntry.COLUMN_ITEM_PRIX + " varchar (20) NOT NULL," +
            FeedEntry.COLUMN_ITEM_ETAT + " Bit NOT NULL,"+
            FeedEntry.COLUMN_ITEM_IDWL + " VARCHAR (20) REFERENCES WL (ID_WL)" + ")";
    public static final String SQL_CREATE_ENTRIES4="CREATE TABLE " + FeedEntry.TABLE_PREFERENCES +"(" +
            FeedEntry.COLUMN_PREFERENCES_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_PREFERENCES_TAILLE_P +  " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_TAILLE_H + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_POINTURE + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_COULEUR + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_CENTREI +" VARCHAR (50)" + ")";
    public static final String SQL_CREATE_ENTRIES5="CREATE TABLE "+ FeedEntry.TABLE_USER + "(" +
            FeedEntry.COLUMN_USER_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL,"+
            FeedEntry.COLUMN_USER_MDP + " VARCHAR (20) NOT NULL" +")";
    public static final String SQL_CREATE_ENTRIES6="CREATE TABLE " + FeedEntry.TABLE_WL + "(" +
            FeedEntry.COLUMN_WL_PSEUDO + " VARCHAR (20) NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_WL_NWL + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_WL_DESCRIPTION + " VARCHAR (20)," +
            FeedEntry.COLUMN_WL_EDIT + " BIT NOT NULL," +
            FeedEntry.COLUMN_WL_IDWL + " VARCHAR (20)" +")";
    public static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_AUTORISATION;
    public static final String SQL_DELETE_ENTRIES2 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_FRIEND;
    public static final String SQL_DELETE_ENTRIES3 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_ITEM ;
    public static final String SQL_DELETE_ENTRIES4 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_PROFIL;
    public static final String SQL_DELETE_ENTRIES5 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_PREFERENCES;
    public static final String SQL_DELETE_ENTRIES6 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_USER;
    public static final String SQL_DELETE_ENTRIES7 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_WL;


}
