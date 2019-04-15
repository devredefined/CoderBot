package com.CodeHub.competitivecoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubMenu extends AppCompatActivity
         {
    Button b1,b2;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progress;
    DatabaseReference databaseReference;
    ArrayList productList;
    Intent i;
    String temp="",title="";//j
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        i=getIntent();
        temp=i.getStringExtra("a");
        title=i.getStringExtra("title");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setIndeterminate(true);
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

        recyclerView = (RecyclerView) findViewById(R.id.rvsm);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        progress.show();

        productList = new ArrayList<>();
//        FirebaseApp.initializeApp(this);
        databaseReference= FirebaseDatabase.getInstance().getReference(temp);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
        // recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MyAdapter(myDataset);
        // recyclerView.setAdapter(mAdapter);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    progress.cancel();
                    for(DataSnapshot productSnapshot: dataSnapshot.getChildren())
                    {
                        Product1 p = productSnapshot.getValue(Product1.class);
                        //Toast.makeText(MainActivity.this, p.getDetail(), Toast.LENGTH_SHORT).show();
                        productList.add(p);
                        //Toast.makeText(SubMenu.this, p.getImage(), Toast.LENGTH_SHORT).show();
                    }
                    mAdapter = new MyAdapter1(SubMenu.this, productList);
                    mAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
