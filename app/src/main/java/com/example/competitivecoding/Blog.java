package com.example.competitivecoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Blog extends AppCompatActivity {
Intent i;
String s;
WebView wv;
String yourData="",title="";
ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        i=getIntent();
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title=i.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        s=i.getStringExtra("b");
        wv=findViewById(R.id.wv);
//        wv.getSettings (). setUseWideViewPort (true);
     //   wv.getSettings (). setLoadWithOverviewMode (true);
//        wv.getSettings (). setSupportZoom (true);


        yourData=s;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//      wv       textView.setText(Html.fromHtml(ss, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
//        }4
        //


        progress.show();
        wv.setVerticalFadingEdgeEnabled(false);
        //mWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        wv.getSettings().setJavaScriptEnabled(true);
        //webSettings.setLoadWithOverviewMode(true);
        //webSettings.setUseWideViewPort(true);
        WebSettings ws = wv.getSettings();
        //webSettings.setDefaultZoom(ZoomDensity.FAR);

      
        wv.loadData(yourData, "text/html", "UTF-8");


        wv.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageCommitVisible(WebView view,
                                            String url) {
                progress.cancel();
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
