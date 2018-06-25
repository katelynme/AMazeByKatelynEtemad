package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class GeneratingActivity extends AppCompatActivity {
    private static final String TAG = "GeneratingActivity";
    ProgressBar progressBar;
    private int progressBarStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Intent i = new Intent(this, PlayAnimationActivity.class);
        Log.v(TAG, "Starting the PlayAnimationActivity");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressBarStatus < 100){
                    progressBarStatus++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                    }
                });
            }
        }).start();

    }

    /**
     * Code for when the back button is pressed, the app should return to the
     * main screen (AMazeActivity).
     */
    public void onBackPressed(){
        Log.v(TAG, "Back button pressed, returning to AMazeActivity");
        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
        finish();
    }
}
