package com.zweber.remotevolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class StringSender extends AppCompatActivity {
    Button button;
    EditText inputField;
    String ipAddress = MainActivity.getIP();
    int portNumber = MainActivity.getPort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_sender);
        button = findViewById(R.id.sendButton);
        inputField = findViewById(R.id.input);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(inputField.getText().toString() != null) {
                    Log.i("ip", ipAddress);
                    Log.i("port", String.valueOf(portNumber));
                    Log.i("input", inputField.getText().toString());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try (Socket socket = new Socket(ipAddress, portNumber)) {
                                Log.i("Connecting", "Attempting Connection...");
                                OutputStream out = socket.getOutputStream();
                                byte[] data = inputField.getText().toString().getBytes(StandardCharsets.UTF_8);
                                out.write(data);
                                PrintWriter write = new PrintWriter(out, true);
                                Log.i("Success", "Message sent!");
                            } catch (UnknownHostException ex) {
                                Log.i("ServerNotFound", "Server not found.");
                            } catch (IOException ex) {
                                Log.i("IOErr", "I/O error: " + ex.getMessage());
                            }
                        }
                    });
                    thread.start();
                } else {
                    //Do something when no text
                    Log.i("NoText", "No text");
                }
            }
        });
    }
}