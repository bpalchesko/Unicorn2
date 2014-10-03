package edu.upenn.cis573.hwk2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends UnicornActivity {
	
	// keeps track of the best time so far
	private static float bestTime = 10000000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_game);
        
        // this method is deprecated but, trust me, it's easier this way
        showDialog(0);
	}
	
	View getScoreboard() {
		return this.findViewById(R.id.scoreboard);
	}
	
    public void onButtonClick(View v) {
    	// this terminates the Activity and goes back to the previous one
    	finish();
    }
    
    protected Dialog onCreateDialog(int id) {
    	if (id == 0) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // this is the message to display
	    	builder.setMessage("Ready?"); 
            // this is the button to display
	    	builder.setPositiveButton(R.string.yes,
	    		new DialogInterface.OnClickListener() {
                       // this is the method to call when the button is clicked 
	    	           public void onClick(DialogInterface dialog, int id) {
                           // this will hide the dialog
	    	        	   dialog.cancel();
	    	        	   // then start the unicorn moving across the screen
	    	               GameView gv = (GameView)findViewById(R.id.gameView);
	    	               BackgroundDrawingTask t = new BackgroundDrawingTask(gv);
	    	               t.execute();
	    	               gv.startTime = System.currentTimeMillis();
	    	           }
	    	         });
    		return builder.create();
    	}
    	else if (id == 1) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // figure out which message to display
            GameView gv = (GameView)findViewById(R.id.gameView);
	    	long time = gv.endTime - gv.startTime;
	    	// a little magic to convert to tenths of a second
	    	float displayTime = (time / 100) / (float)10.0;
	    	if (bestTime == 10000000) {
	    		bestTime = displayTime;
		    	builder.setMessage("Great job! You finished in " + displayTime + " seconds"); 
	    	}
	    	else if (displayTime <= bestTime) {
	    		bestTime = displayTime;
		    	builder.setMessage("You finished in " + displayTime + " seconds! That's the fastest so far!"); 
	    	}
	    	else builder.setMessage("Great job! You finished in " + displayTime + " seconds; the best so far is " + bestTime);
	    	
            // this is the button to display
	    	builder.setPositiveButton(R.string.yes,
	    		new DialogInterface.OnClickListener() {
                       // this is the method to call when the button is clicked 
	    	           public void onClick(DialogInterface dialog, int id) {
                           // this will hide the dialog
	    	        	   dialog.cancel();
	    	        	   // this will terminate this Activity
	    	        	   finish();
	    	           }
	    	         });
    		return builder.create();
    		
    	}
    	else return null;
    }
    
    
}


