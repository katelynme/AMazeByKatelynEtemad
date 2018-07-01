package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class PlayManuallyActivity extends AppCompatActivity {
    private static final String TAG = "PlayManuallyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_manually);
    }

    /**
     * Takes us to the finish activity once the user has successfully exited the maze
     */
    public void onFinish(){
        Intent intent = new Intent(this, FinishActivity.class);
        Log.v(TAG, "Starting finish activity");
        startActivity(intent);
    }

    /**
     * Takes us back to the title screen
     */
    public void switchToTitle(){
        Intent intent = new Intent(this, AMazeActivity.class);
        Log.v(TAG, "Returning to the title screen");
        startActivity(intent);
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
