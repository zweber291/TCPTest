package com.zweber.remotevolume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText ip;
    EditText port;
    String portNumberString;
    static String ipAddress;
    static int portNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.connectButton);
        ip = findViewById(R.id.ipAddress);
        port = findViewById(R.id.portNumber);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                ipAddress = ip.getText().toString();
                portNumberString = port.getText().toString();
                portNumber = Integer.parseInt(portNumberString);
                startActivity(new Intent(MainActivity.this, StringSender.class));
                if (ipAddress == null || portNumberString == null) {
                    //Something here when either text box is empty
                }
            }
        });
    }

    public static String getIP() {
        return ipAddress;
    }
    public static int getPort() {
        return portNumber;
    }
}