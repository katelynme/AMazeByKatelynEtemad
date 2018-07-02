package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.Globals;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad.StubOrder;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeFactory;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.Order;

public class GeneratingActivity extends AppCompatActivity implements Order {
    private static final String TAG = "GeneratingActivity";
    ProgressBar progressBar;
    private int progressBarStatus = 0;
    private Handler handler = new Handler();
    private String builder;
    private Builder mazeBuilder;
    private String driver;
    private int level;
    //shared mazeConfig to pass on to the PlayManuallyActivity
    MazeConfiguration mazeConfig;

    private MazeFactory mazeFactory;

    private Thread background;

    Handler progressHandler = new Handler() {
        public void handleMessage(Message msg) {
            progressBar.incrementProgressBy(30);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        mazeConfig = Globals.mazeConfig;
        Log.v(TAG, "Progress bar and maze configuration set");

        //retrieving the variables passed from the previous activity
        driver = (String)getIntent().getStringExtra("Driver");
        builder = (String)getIntent().getStringExtra("Builder");
        level = (Integer)getIntent().getIntExtra("Level", 0);
        Log.v(TAG, "Passed in driver: " + driver + ", builder: " + builder + ", level: " + level);

        //create a background thread to take care of the progressbar's status
        background = new Thread (new Runnable() {
            public void run() {
                while (progressBar.getProgress() < 100) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressHandler.sendMessage(progressHandler.obtainMessage());
                }
            }
        });
        background.start();

        mazeBuilder = this.getBuilder();
        mazeFactory = new MazeFactory(true);
        StubOrder stubOrder = new StubOrder(level, mazeBuilder, true);
        mazeFactory.order(stubOrder);
        mazeFactory.waitTillDelivered();
        mazeConfig = stubOrder.getMazeConfiguration();
        deliver(mazeConfig);
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

    /**
     * Returns the current skill level
     * @return
     */
    @Override
    public int getSkillLevel() {
        return level;
    }

    /**
     * Depending on the string given by the previous activity, this method
     * will return the actual builder type associated with the string.
     * @return
     */
    @Override
    public Builder getBuilder() {
        if(builder.equalsIgnoreCase("Prim")){
            Log.v(TAG, "Builder set to Prim");
            return Builder.Prim;
        }
        else if(builder.equalsIgnoreCase("Kruskal")){
            Log.v(TAG, "Builder set to Kruskal");
            return Builder.Kruskal;
        }
        else{
            Log.v(TAG, "Builder set to DFS");
            return Builder.DFS;
        }
    }

    @Override
    public boolean isPerfect() {
        return false;
    }

    /**
     * This method sets the global variable for the maze configuration for access between
     * activities, and starts the next activity (PlayManuallyActivity)
     * @param mazeConfig
     */
    @Override
    public void deliver(MazeConfiguration mazeConfig) {
        Globals.mazeConfig = mazeConfig;
        Log.v(TAG, "Starting PlayManuallyActivity");
        Intent intent = new Intent(this, PlayManuallyActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * We update the progressBarStatus percentage with the parameter percentage and
     * appropriately set the progressBar to this.
     * @param percentage of job completion
     */
    @Override
    public void updateProgress(int percentage) {
        Log.v(TAG, "UpdateProgress called, percentage: " + percentage);
        progressBarStatus = percentage;
        progressBar.setProgress(progressBarStatus);
    }
}
