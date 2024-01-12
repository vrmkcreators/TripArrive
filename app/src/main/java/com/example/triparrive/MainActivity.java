package com.example.triparrive;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.MenuItem;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedCallback;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    WebView myWeb;

    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        myWeb = findViewById(R.id.myWeb);
        myWeb.getSettings().setJavaScriptEnabled(true);
        myWeb.setWebViewClient(new WebViewClient());
        myWeb.loadUrl("https://triparrive.com/");

        setupOnBackPressedCallback();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.myWeb) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupOnBackPressedCallback() {
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Check if the WebView can go back
                if (myWeb.canGoBack()) {
                    myWeb.goBack(); // Navigate back in WebView
                } else {
                    finish(); // Exit the app if WebView cannot go back
                }
            }
        };
        onBackPressedDispatcher.addCallback(this, callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (event.getAction()==KeyEvent.ACTION_DOWN){
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myWeb.canGoBack()){
                        myWeb.goBack();
                    }
                    else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode,event);
    }


}