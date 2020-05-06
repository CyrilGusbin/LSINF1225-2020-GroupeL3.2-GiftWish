//Ceci est l'interface du profil.

package com.example.wishlist.Interface;
;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.example.wishlist.R;

import java.io.File;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Profile extends AppCompatActivity {

    private static final int REQUEST_GET_SINGLE_FILE = 1 ;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final int REQUEST_IMAGE_CAPTURE = 1;

        //Affiche le pseudo de l'user connecté.
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.pseudo_user);
        textView.setText(message);

        //Ouvre notre galerie photo, nous permettant de choisir une photo.
        //TODO : Pouvoir mettre à jour l'interface avec la nouvelle photo
        //TODO : Enegistrer l'image dans la base de donnée
        //TODO : Améliorer stablilité
        this.play = findViewById(R.id.edit_photo);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Profile.class);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
            }
        });

                //Permet à l'utilisateur de modifier son profil.
        this.play = findViewById(R.id.edit_profile);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), EditProfile.class);
                Intent intent = getIntent();
                String getPseudo = intent.getStringExtra(EXTRA_MESSAGE);
                otherActivity.putExtra(EXTRA_MESSAGE, getPseudo);
                startActivity(otherActivity);
            }
        });

        this.play = findViewById(R.id.delete_account);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), DeleteAccount.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_GET_SINGLE_FILE) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    // Set the image in ImageView
                    ImageView photo = findViewById(R.id.photo_user);
                    photo.setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
