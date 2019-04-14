package com.example.coderbot;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class About extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
String complite="";
String arr[]=new String[1];
ImageView gau,sau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        gau=findViewById(R.id.circle_gaurav);
        sau=findViewById(R.id.circle_saurabh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");
        databaseReference=FirebaseDatabase.getInstance().getReference("about");
         databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // Toast.makeText(About.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                complite=dataSnapshot.getValue().toString();
                Log.d("1234",complite);
                imagesetter(complite);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, arr[0]+"c", Toast.LENGTH_SHORT).show();
        String link[]=complite.split("_split_");
        Log.d("1234",complite);

    }

    private void imagesetter(String complite) {
        String ar[]=complite.split("_split_");
        Glide.with(this).load(ar[0]).apply(RequestOptions.circleCropTransform()).into(gau);
        Glide.with(this).load(ar[1]).apply(RequestOptions.circleCropTransform()).into(sau);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("1234", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
