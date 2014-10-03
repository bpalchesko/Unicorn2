package edu.upenn.cis573.hwk2;

import android.os.AsyncTask;

/**
 * This class is responsible for making the unicorn appear to move.
 * When "exec" is called on an object of this class, "doInBackground" gets
 * called in a background thread. It just waits 10ms and then updates the
 * image's position. Then "onPostExecute" is called
 */
public class BackgroundDrawingTask extends AsyncTask<Integer, Void, Integer> {

	private GameView gameView;
	private Image unicorn;
	
	public BackgroundDrawingTask(GameView gameView) {
		this.gameView = gameView;
		this.unicorn = gameView.getUnicorn();
	}
	
	// this method gets run in the background
	protected Integer doInBackground(Integer... args) {
		try { 
			// note: you can change these values to make the unicorn go faster/slower
			Thread.sleep(10);
			
			unicorn.setX(unicorn.getX() + 10);
			unicorn.setY(unicorn.getY() + gameView.getYChange());
		} 
		catch (Exception e) { }
		// the return value is passed to "onPostExecute" but isn't actually used here
		return 1; 
	}
	
	// this method gets run in the UI thread
	protected void onPostExecute(Integer result) {
		// redraw the View
		gameView.invalidate();
		if (gameView.getScore() < 10) {
			// need to start a new thread to make the unicorn keep moving
			BackgroundDrawingTask task = new BackgroundDrawingTask(gameView);
			task.execute();
		}
		else {
			// game over, man!
			if (gameView.getParentActivity() instanceof GameActivity) {
				((GameActivity) gameView.getParentActivity()).endTime = System
						.currentTimeMillis();
			}
			// these methods are deprecated but it's okay to use them... probably.
			gameView.getParentActivity().removeDialog(1);
			gameView.getParentActivity().showDialog(1);
		}
	}  
}
