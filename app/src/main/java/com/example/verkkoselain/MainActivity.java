package com.example.verkkoselain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    WebView web;
    ImageButton refresh;
    ImageButton src;
    ImageButton shout;
    ImageButton init;
    ImageButton prevBtn;
    ImageButton nextBtn;
    EditText input;
    String currentUrl = "https://google.fi";
    String prev;
    String next;
    boolean prevBool = false;
    boolean nextBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.web_browser);
        refresh = findViewById(R.id.refresh_btn);
        src = findViewById(R.id.src_btn);
        input = findViewById(R.id.url_txt);
        shout = findViewById(R.id.shoutout);
        init = findViewById(R.id.init);
        prevBtn = findViewById(R.id.prev);
        nextBtn = findViewById(R.id.next);

        nextBtn.setEnabled(false);
        prevBtn.setEnabled(false);
        web.setWebViewClient(new WebViewClient());

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(currentUrl);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev = currentUrl;
                prevBtn.setEnabled(true);
                search();
            }
        });

        init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("javascript:initialize()");
            }
        });

        shout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("javascript:shoutOut()");
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next = currentUrl;
                currentUrl = prev;
                web.loadUrl(currentUrl);
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(false);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl(next);
                nextBtn.setEnabled(false);
            }
        });
    }

    void search(){
        String userInput = input.getText().toString();
        if(!userInput.startsWith("http://www.") && !userInput.startsWith("https://www.") && !userInput.endsWith(".html")){
            userInput = "https://www." + userInput;
        }else if(userInput.endsWith(".html")){
            web.loadUrl("file:///android_asset/" + userInput);
            return;
        }
        input.setText("");
        currentUrl = userInput;
        web.loadUrl(currentUrl);
    }

    void refresh(){
        web.loadUrl(currentUrl);
    }
}