package com.misterjedu.twoactivitiesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.misterjedu.twoactivitiesproject.extra.Reply";

    private EditText mReply;

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        mReply = findViewById(R.id.editText_second);

//       Initialize a get intent to get intents passed down from any another activity
        Intent intent = getIntent();

//      Get a specific intent using the class it's coming from and the key defined
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

//        Get a reference to the text view
        TextView textView = findViewById(R.id.text_message);

//        Set the text view text.
        textView.setText(message);
    }

    //   Testing Activity Life Cycle
    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onReStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, " on Destroy");
    }



    //    Sending Reply to the Main Activity
    public void returnReply(View view) {

        String reply = mReply.getText().toString();

        Intent replyIntent = new Intent();

        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);

        Log.d(LOG_TAG, "End SecondActivity");

        finish();
    }
}