package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.R;

public class PlayAnimationActivity extends AppCompatActivity {
    private static final String TAG = "PlayAnimationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_animation);
    }

    public void onShortcutClick(View view){
        Intent intent = new Intent(this, FinishActivity.class);
        Log.v(TAG, "Starting finish activity");
        startActivity(intent);
    }

    public void onBackPressed(){
        Log.v(TAG, "Back button pressed, returning to AMazeActivity");
        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
        finish();
    }
}
