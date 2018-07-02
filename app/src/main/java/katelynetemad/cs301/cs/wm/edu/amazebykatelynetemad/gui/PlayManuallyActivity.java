package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.Constants;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.Globals;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.MazePanel;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.StatePlaying;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;

public class PlayManuallyActivity extends AppCompatActivity {
    private static final String TAG = "PlayManuallyActivity";
    MazePanel panel;
    MazeConfiguration mazeConfig;
    StatePlaying statePlaying = new StatePlaying();
    Switch map, solution, walls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_manually);

        mazeConfig = Globals.mazeConfig;
        panel = findViewById(R.id.mazePanel);
        statePlaying.setMazeConfiguration(mazeConfig);
        statePlaying.start(panel);

        map = findViewById(R.id.mapSwitch);
        map.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                statePlaying.keyDown(Constants.UserInput.ToggleFullMap, 0);
            }
        });

        solution = findViewById(R.id.solutionSwitch);
        solution.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                statePlaying.keyDown(Constants.UserInput.ToggleSolution, 0);
            }
        });

        walls = findViewById(R.id.wallSwitch);
        walls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                statePlaying.keyDown(Constants.UserInput.ToggleLocalMap, 0);
            }
        });
    }

    /**
     * Calls statePlaying to respond to the user input of the up button being clicked
     * @param v
     */
    public void onUpClick(View v){
        Log.v(TAG, "Up button clicked");
        statePlaying.keyDown(Constants.UserInput.Up, 0);
    }

    /**
     * Calls statePlaying to respond to the user input of the down button being clicked
     * @param v
     */
    public void onDownClick(View v){
        Log.v(TAG, "Down button clicked");
        statePlaying.keyDown(Constants.UserInput.Down, 0);
    }

    /**
     * Calls statePlaying to respond to the user input of the right button being clicked
     * @param v
     */
    public void onRightClick(View v){
        Log.v(TAG, "Right button clicked");
        statePlaying.keyDown(Constants.UserInput.Right, 0);
    }

    /**
     * Calls statePlaying to respond to the user input of the left button being clicked
     * @param v
     */
    public void onLeftClick(View v){
        Log.v(TAG, "Left button clicked");
        statePlaying.keyDown(Constants.UserInput.Left, 0);
    }

    /**
     * Takes us to the finish activity once the user has successfully exited the maze
     */
    public void onFinish(){
        Intent intent = new Intent(this, FinishActivity.class);
        Log.v(TAG, "Starting finish activity");
        startActivity(intent);
        finish();
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
