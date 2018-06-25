package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class AMazeActivity extends AppCompatActivity {
    private static final String TAG = "AMazeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);
    }

    /**
     * Method to switch to the generating activity with the proper specifications
     * when the explore button is clicked
     */
    public void onExploreClick(View view){
        Intent i = new Intent(this, GeneratingActivity.class);
        Log.v(TAG, "Starting the generating activity through explore");
        startActivity(i);
    }

    /**
     * Method to switch to the generating activity with the proper specifications
     * when the revisit button is clicked
     */
    public void onRevisitClick(View view){
        Intent i = new Intent(this, GeneratingActivity.class);
        Log.v(TAG, "Starting the generating activity through revisit");
        startActivity(i);
    }
}
