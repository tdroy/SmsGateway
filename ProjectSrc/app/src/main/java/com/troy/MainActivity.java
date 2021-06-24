package com.troy;

import androidx.appcompat.app.AppCompatActivity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private WebServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.OutTextView);
        textView.setEnabled(false);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.append("********************************");
        textView.append("\n*** Troy Android SmsServer ***");
        textView.append("\n********************************");
        textView.append("\nStarting server...");

        server = new WebServer(this, (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE));
        try {
            server.start();
        } catch(IOException ioe) {
            Log.w("Httpd", "The server could not start.");
            textView.append("\nThe server could not start.");
        }
        Log.w("Httpd", "Web server initialized.");
        textView.append("\nServer started.");

        /*SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("9923345295", null, "troy message", null, null);
        textView.setText(textView.getText() + "\nMessage send to..");*/
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (server != null)
            server.stop();
    }
}