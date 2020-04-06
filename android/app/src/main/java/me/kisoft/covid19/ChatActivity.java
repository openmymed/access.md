package me.kisoft.covid19;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {
    // For more info about this library,
    // check documentation at https://github.com/timigod/android-chat-ui.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("Chat");
        final ChatView chatView = (ChatView) findViewById(R.id.chat_view);

        //for sending messages..
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                //This method adds the messages to chat by default.
                //In the method sendMessage(), you can now perform whatever logic to send messages
                // i.e make an HTTP request or send the message over a socket connection.
                return true;
            }
        });
    }
}
