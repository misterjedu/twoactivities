package com.misterjedu.twoactivitiesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    Define Key for the Intent
    public static final String EXTRA_MESSAGE = "com.misterjedu.twoactivitiesproject.extra.Message";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();;

    //  Initialize the reference to the Edit Text view
    private EditText mMessageEditText;


//   Getting data back from the Second Activity,  Grab reference to both views
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//      Get reference to the Edit Text view
        mMessageEditText = findViewById(R.id.editText_main);

//       Reference to the views in activity main (Made invisible at start)
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

//        Restore the state when recreating the instance
        if (savedInstanceState != null) {

            boolean isVisible = savedInstanceState.getBoolean("reply_visible");

            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }

        }


        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }
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



    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
//       Get the text from the Edit Text view
        String message = mMessageEditText.getText().toString();
//        Pass the message from the Edit Text view along with the defined key into the intent Extras
        intent.putExtra(MainActivity.EXTRA_MESSAGE, message);

//       Start Activity with intent
        startActivityForResult(intent, TEXT_REQUEST);


        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {

                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}