package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class GeneratingActivity extends AppCompatActivity {
    private static final String TAG = "GeneratingActivity";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setProgress(50);
        Intent i = new Intent(this, PlayAnimationActivity.class);
        Log.v(TAG, "Starting the PlayAnimationActivity");
        startActivity(i);
    }

    /**
     * Code for when the back button is pressed, the app should return to the
     * main screen (AMazeActivity).
     */
    public void onBackPressed(){
        Log.v(TAG, "Starting the generating activity through explore");
    }
}
