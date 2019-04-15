package com.example.coderbot;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RequestTopic extends AppCompatActivity {

    EditText email,topic;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_topic);
        topic=findViewById(R.id.edittext_requestTopic_main_topic);
        email=findViewById(R.id.edittext_requesttopic_email);
        b1=findViewById(R.id.button_request_topic1);
        getSupportActionBar().setTitle("Request Topic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listner();
    }

    private void listner() {



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topic.getText().toString().isEmpty())
                {
                    topic.setError("Required");
                }
                else if(email.getText().toString().isEmpty())
                {
                    email.setError("Required");
                }
                else {
                    String msg=topic.getText().toString();
                    msg="UserId :  "+email.getText().toString()+"     "+msg;

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + "dev.redefined@gmail.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request Topic CodeHub");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(RequestTopic.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }

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
