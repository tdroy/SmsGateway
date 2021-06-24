package com.troy;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.SmsManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {
    Context ctx;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    //sdf.format(new Date());

    public WebServer(Context context, WifiManager wifiManager )
    {
        super(8080);
        this.ctx = context;
        System.out.println("Inside WebServer Constructor..");
        WifiManager wifiMgr = (WifiManager) wifiManager;
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();

        TextView textView = (TextView) ((Activity)ctx).findViewById(R.id.OutTextView);
        //textView.setTextSize(8);
        textView.append("\nListening on " + Formatter.formatIpAddress(ip) + " 8080");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String answer = "Error..";
        TextView textView = (TextView) ((Activity)ctx).findViewById(R.id.OutTextView);

        if (session.getMethod() == Method.GET) {
            String cell_number = session.getParameters().get("cell_number").get(0);
            String message = session.getParameters().get("message").get(0);

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(cell_number, "GT-TROYSMS", message, null, null);
                textView.append("\n" + Calendar.getInstance().getTime() + " Message send to " + cell_number);
                answer = Calendar.getInstance().getTime() + " Message sent to " + cell_number;

            } catch (Exception e) {
                Log.w("Httpd", e.toString());
            }

        }
        //return new NanoHTTPD.Response(answer);
        return newFixedLengthResponse(answer);
    }
}



