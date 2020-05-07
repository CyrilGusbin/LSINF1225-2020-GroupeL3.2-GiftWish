package com.example.wishlist.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.example.wishlist.Backend.FeedReaderContract;
import com.example.wishlist.Backend.FeedReaderDbHelper;
import com.example.wishlist.Backend.ImageToBlob;
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
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_PROFIL, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (newRowId==-1){
            db.close();
            Log.e("Création profil", "failed");
            return false;
        }
        db.close();
        Log.e("Création profil", "suceed");
        return true;
    }
    public String[] get_wishlists(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_WL_NWL
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO+ " = ?";
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_WL,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
        }
    public String[] get_wishes(String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        String[] selectionArgs = {idwl};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public String obtain_idwl(String pseudo, String nwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_WL_IDWL

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_WL_NWL+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO + " = ?";
        String[] selectionArgs = {
                nwl,
                pseudo
        };
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_WL,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        String lst = null;
        if (!cursor.isAfterLast()) {
            lst =cursor.getString(0); 
        }
        cursor.close();
        return lst;

    }
    public boolean create_wishlist(String pseudo, String nwl, String desc, int edit, String id ){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (nwl.length()==0){
            return false;
        }
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_NWL, nwl);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_DESCRIPTION, desc);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_EDIT, edit);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_IDWL, id);
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_WL, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        if(newRowId==-1){
            return false;
        }
        return true;

    }
    public boolean create_item(String IID, String item, String desc,String prix,String IDWL){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (item.length()==0){
            return false;
        }
        if (prix.length()==0){
            return false;
        }
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_ID, IID);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM, item);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_DESCRIPTION, desc);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_PRIX, prix);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_ETAT, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL, IDWL);
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_ITEM, null, values);
        db.close();
        if(newRowId==-1){
            return false;
        }
        return true;
    }
    public void delete_item(String iid){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_ID + " = ?";
        String[] selectionArgs={iid};
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_ITEM, selection, selectionArgs);


    }
    public void delete_wl(String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_ID

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        String[] selectionArgs = {idwl};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        int e=cursor.getCount();
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        int i=0;
        while(i<e){
            Log.e("item", Liste[i]);
            delete_item(Liste[i]);
            i+=1;
            Log.e("delete", "line");
        }
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_WL_IDWL+ " = ?";
        String[] selectionArgs2={idwl};
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_WL, selection2, selectionArgs2);
        db.close();
    }

    public void updateProfilePicture(Bitmap profilePicture){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Photo", ImageToBlob.getBytes(profilePicture));
        String selection = " LIKE? ";
        String[]selectionArg = {String.valueOf(profilePicture)};
        db.update("TABLE_PROFILE", values, selection, selectionArg);
    }
    public String[] get_friendlist(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2,
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO,
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+ " = ? Or " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2+ " = ? AND "  + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+" = ? " ;
        String[] selectionArgs = {pseudo,"1",pseudo,"1"};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            String lst2=cursor.getString(1);
            if(lst==pseudo){
                Liste[ind]=lst2;
                Log.e("ligne", lst2);
            }
            else{
                Liste[ind]=lst;
                Log.e("ligne", lst);
            }
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public boolean friend_request(String pseudo, String pseudo2){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND,
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST,
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2+ " = ? OR " +
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ?";
        String[] selectionArgs = {pseudo, pseudo2, pseudo2, pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        if (cursor.getInt(0)==0 && cursor.getInt(1)==0){
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST, 1);
            String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST + " = ?";
            String[] selectionArgs2= { "0" };
            int count = db.update(
                    FeedReaderContract.FeedEntry.TABLE_FRIEND,
                    values,
                    selection2,
                    selectionArgs2);
            return true;
        }
        return false;
        }
        catch(Exception e){
            return false;
        }

    }
    public String[]  ALL_users(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO+ " != ? " ;
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
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public void connection_all_other_users(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] everyUser = ALL_users(pseudo);
        for (int i=0; i<= (everyUser.length)-1; i++) {
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO, pseudo);
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2, everyUser[i]);
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND, "0");
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST, "0");
            db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_FRIEND, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }

    }
    public String[] get_demands(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2,
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO,
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+ " = ? AND " +  FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST + " = ? OR "+
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2+ " = ? AND "  + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+" = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST + " = ?";
        String[] selectionArgs = {pseudo,"0","1",pseudo,"0","1"};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            String lst2=cursor.getString(1);
            if(lst==pseudo){
                Liste[ind]=lst2;
                Log.e("ligne", lst2);
            }
            else{
                Liste[ind]=lst;
                Log.e("ligne", lst);
            }
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
}


