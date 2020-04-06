package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    public static EditText txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button clickButton = (Button) findViewById(R.id.finish_button);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"CLICK",Toast.LENGTH_SHORT).show();
                finish();


            }
        });
    }

    @Override public void onStart(){
        super.onStart();
        Log.d("INFO","onStart() called");
        Toast.makeText(getApplicationContext(),"onStart() called",Toast.LENGTH_SHORT).show();
    }

    @Override public void onRestart(){
        super.onRestart();
        Log.d("INFO","onRestart() called");
        Toast.makeText(getApplicationContext(),"onRestart() called",Toast.LENGTH_SHORT).show();
    }

    @Override public void onResume(){
        super.onResume();
        Log.d("INFO","onResume() called");
        Toast.makeText(getApplicationContext(),"onResume() called",Toast.LENGTH_SHORT).show();
    }
    @Override public void onPause(){
        super.onPause();
        Log.d("INFO","onPause() called");
        Toast.makeText(getApplicationContext(),"onPause() called",Toast.LENGTH_SHORT).show();
    }

    @Override public void onStop(){
        super.onStop();
        Log.d("INFO","onStop() called");
        Toast.makeText(getApplicationContext(),"onStop() called",Toast.LENGTH_SHORT).show();
    }

    @Override public void onDestroy(){
        super.onDestroy();
        Log.d("INFO","onDestroy() called");
        Toast.makeText(getApplicationContext(),"onDestroy() called",Toast.LENGTH_SHORT).show();
    }



}
