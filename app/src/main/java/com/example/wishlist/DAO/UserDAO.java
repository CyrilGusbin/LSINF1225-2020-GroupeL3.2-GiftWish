package com.example.wishlist.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.wishlist.Backend.FeedReaderContract;
import com.example.wishlist.Backend.FeedReaderDbHelper;
import com.example.wishlist.Backend.USER;


public class UserDAO {
    FeedReaderDbHelper dbHelper;
    public UserDAO(Context context) {

        dbHelper = new FeedReaderDbHelper(context);
    }
    public boolean CreateFirstUser (USER user,String pseudo, String mdp){
        boolean ret=false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_USER_MDP
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO + " = ?";
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_USER,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            if (lst.equals(mdp)){
                user.Pseudo=pseudo;
                user.MDP=mdp;
                ret=true;
                Log.e("try to connect", "connected");
                }
            }
        cursor.close();

        db.close();
        return ret;
    }

    public boolean AddUserDB(String pseudo, String mdp){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_USER_MDP, mdp);
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (newRowId==-1){
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    public boolean AddDataDB(String pseudo, String name, String firstname, String age){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_NOM, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_PRENOM, firstname);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_AGE, age);
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_PROFIL, null, values);
        if (newRowId==-1){
            db.close();
            Log.e("Création profil", "failed");
            return false;
        }
        db.close();
        Log.e("Création profil", "suceed");
        return true;
    }
}
