package com.CodeHub.competitivecoding;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Button b1,b2;
    ProgressDialog progress;
    AlertDialog.Builder builder;
    RecyclerView recyclerView;
    boolean check=false;
    private RecyclerView.Adapter mAdapter;
    AlertDialog alert;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;
    FirebaseDatabase mFirebaseInstance;
    ArrayList productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        String myDataset[]={"Coding","Algorithms","Quizzes","Competitions","CodeChef","Hackerrank","Hackerearth"
//                ,"Geeksforgeeks","Project Euler","TopCoder","Coderbyte","CodeEval","Codewars",
//                "artificial intelligence",
//                "computer technology",
//                "computer-aided learning",
//                "computer-aided testing",
//                "cybernetics",
//                "data processing",
//                "information retrieval",
//                "information technology",
//                "natural language processing",
//                "neural networks",
//                "operating systems",
//                "programming",
//                "programming languages",
//                "robotics",
//                "simulation",
//                "systems analysis"};


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        productList = new ArrayList<>();
//        FirebaseApp.initializeApp(this);
        databaseReference= mFirebaseInstance.getInstance().getReference("RecyclerView");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
       // recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MyAdapter(myDataset);
       // recyclerView.setAdapter(mAdapter);
         builder = new AlertDialog.Builder(this);
        builder.setMessage("Please connect to internet and ...")
                .setCancelable(false)
                .setPositiveButton("Connected ?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                        //do things
                        //onCreate();
                        Intent intent = getIntent();

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                });
        alert = builder.create();



        check=false;
        try {
            check=isConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recall();




        }

    private void recall() {
        check=false;
        try {
            check=isConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(check){
            progress.show();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        progress.cancel();
                        for(DataSnapshot productSnapshot: dataSnapshot.getChildren())
                        {
                            Product p = productSnapshot.getValue(Product.class);
                            //Toast.makeText(MainActivity.this, p.getDetail(), Toast.LENGTH_SHORT).show();
                            productList.add(p);


                        }
                        mAdapter = new MyAdapter(MainActivity.this, productList);

                        mAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mAdapter);
//                    mAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
//progress.cancel();
            alert.show();

         //   Toast.makeText(this, "Please connect  to Internet", Toast.LENGTH_SHORT).show();
        }


    }


//    private void setupProgress(){
//        progress.show();
//

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            exit(0);
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        }  else if (id == R.id.nav_aboutus) {
            Intent i=new Intent(MainActivity.this,About.class);
            startActivity(i);

        }  else if (id == R.id.nav_share) {
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_TEXT,"check download app");
//            intent.setType("text/plain");
//            startActivity(intent);

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My App Name");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "ijnijsd hdfsnjinfids sifnfjisni ");
            startActivity(Intent.createChooser(sharingIntent, "Share app via"));

        }
        else if(id==R.id.nav_request_topic){
            Intent i=new Intent(MainActivity.this,RequestTopic.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }


}
