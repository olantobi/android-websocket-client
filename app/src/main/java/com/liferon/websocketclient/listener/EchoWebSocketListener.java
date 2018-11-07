package com.liferon.websocketclient.listener;

import com.liferon.websocketclient.MainActivity;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private MainActivity mainActivity;

    //public EchoWebSocketListener() {}

    public EchoWebSocketListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Hello, it's Tobi!");
        webSocket.send("What's up?");
        webSocket.send(ByteString.decodeHex("deadbeef"));
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        mainActivity.output("Receiving: "+text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        mainActivity.output("Receiving: "+bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        mainActivity.output("Closing: "+code+"/"+reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        mainActivity.output("Error: "+t.getMessage());
    }

//    private void output(String message) {
//        Log.i("Output: ", message);
//
//    }
}
