package com.example.coderbot;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

public class Blog extends AppCompatActivity {
Intent i;
String s;
WebView wv;
String yourData="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        i=getIntent();
        s=i.getStringExtra("b");
        wv=findViewById(R.id.wv);
        yourData=s;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            textView.setText(Html.fromHtml(ss, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
//        }
        wv.loadData(yourData, "text/html", "UTF-8");
    }
}
