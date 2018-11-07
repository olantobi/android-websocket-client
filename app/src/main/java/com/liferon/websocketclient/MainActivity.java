package com.liferon.websocketclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liferon.websocketclient.listener.EchoWebSocketListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView outputTextView;
    private OkHttpClient websocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startButton = (Button) findViewById(R.id.start);
        outputTextView = (TextView) findViewById(R.id.output);
        websocketClient = new OkHttpClient();
        
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    private void start() {
        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        EchoWebSocketListener listener = new EchoWebSocketListener(this);
        WebSocket ws = websocketClient.newWebSocket(request, listener);

        websocketClient.dispatcher().executorService().shutdown();
    }

    public void output(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                outputTextView.setText(outputTextView.getText().toString() + "\n\n"+ text);
            }
        });
    }
}
