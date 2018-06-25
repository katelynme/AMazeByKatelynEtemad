package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class AMazeActivity extends AppCompatActivity {
    private static final String TAG = "AMazeActivity";
    SeekBar seekBar;
    TextView textView;
    Spinner builderSpinner;
    Spinner driverSpinner;
    ArrayList<String> builders;
    ArrayList<String> drivers;
    String builder = "DFS";
    String driver = "manual";
    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);

        setSeekBar();

        builders = new ArrayList<>();
        builders.add("Prim");
        builders.add("Kruskal");
        builders.add("DFS");

        drivers = new ArrayList<>();
        drivers.add("WallFollower");
        drivers.add("Wizard");
        drivers.add("Manual");

        setBuilderSpinner();
        setDriverSpinner();

    }

    /**
     * This method tracks the users input to the seek bar and changes the level of the maze
     * to be generated accordingly.
     */
    public void setSeekBar(){
        seekBar = (SeekBar)findViewById(R.id.skillLevel);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Level: " + seekBar.getProgress() + " of " + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        level = progress;
                        textView.setText("Level: " + progress + " of " + seekBar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        textView.setText("Level: " + level + " of " + seekBar.getMax());
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textView.setText("Level: " + level + " of " + seekBar.getMax());
                        Log.v(TAG, "Difficulty set to " + level);
                    }
                }
        );
    }

    /**
     * Set up the builder spinner with an array of options for different builder modes.
     * Also listen for the option selected and set the builder string to that.
     */
    public void setBuilderSpinner(){
        builderSpinner = (Spinner)findViewById(R.id.selectBuilder);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_item, builders
        );
        builderSpinner.setAdapter(adapter);
        builderSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        builder = parent.getItemAtPosition(position).toString();
                        Log.v(TAG, "Builder " + builder + " selected");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    /**
     * Set up the driver spinner with an array of options for different driver methods.
     * Also listen for the option selected and set the driver string to that.
     */
    public void setDriverSpinner(){
        driverSpinner = (Spinner)findViewById(R.id.selectDriver);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_item, drivers
        );
        driverSpinner.setAdapter(adapter);
        driverSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        driver = parent.getItemAtPosition(position).toString();
                        Log.v(TAG, "Driver " + driver + " selected");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
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
