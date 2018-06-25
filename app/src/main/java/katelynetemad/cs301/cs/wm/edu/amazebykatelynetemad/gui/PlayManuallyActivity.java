package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class PlayManuallyActivity extends AppCompatActivity {
    private static final String TAG = "PlayManuallyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_manually);
    }

    /**
     * Temporary button, if the shortcut button is clicked we are taken to the finishing
     * page (FinishActivity). This is in place of the graphics that will be implemented
     * later.
     * @param view
     */
    public void onShortcutClick(View view){
        Intent intent = new Intent(this, FinishActivity.class);
        Log.v(TAG, "Starting finish activity");
        startActivity(intent);
    }
}
