package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class FinishActivity extends AppCompatActivity {
    private static final String TAG = "FinishActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    public void onPlayAgainPressed(View view){
        Intent intent = new Intent(this, AMazeActivity.class);
        Log.v(TAG, "Play again button pressed, returning to AMazeActivity");
        startActivity(intent);
    }

    public void onBackPressed(){
        Log.v(TAG, "Back button pressed, returning to AMazeActivity");
        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
        finish();
    }
}
