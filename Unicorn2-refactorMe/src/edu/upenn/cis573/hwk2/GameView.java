package edu.upenn.cis573.hwk2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class GameView extends View {
    //private Bitmap image;
    private Stroke stroke = new Stroke();
    private boolean killed = false;
    private boolean newUnicorn = true;
    private int score = 0;
    private int yChange = 0;
    private Image unicorn = new Image(getResources());
    
    public long startTime;
    public long endTime;

    public GameView(Context context) {
	    super(context);
	    setBackgroundResource(R.drawable.space);
    }
    
    public GameView(Context context, AttributeSet attributeSet) {
    	super(context, attributeSet);
	    setBackgroundResource(R.drawable.space);
    }
    
    /*
     * This method is automatically invoked when the View is displayed.
     * It is also called after you call "invalidate" on this object.
     */
    protected void onDraw(Canvas canvas) {    	

    	// resets the position of the unicorn if one is killed or reaches the right edge
    	if (newUnicorn || unicorn.getX() >= this.getWidth()) {
    		unicorn.setX(-150);
    		unicorn.setY((int)(Math.random() * 200 + 200));
    		yChange = (int)(10 - Math.random() * 20);
    		newUnicorn = false;
    		killed = false;
    	}

		// show the exploding image when the unicorn is killed
    	if (killed) {
    		canvas.drawBitmap(unicorn.getImage(killed), unicorn.getX(), unicorn.getY(), null);
    		newUnicorn = true;
    		try { Thread.sleep(10); } catch (Exception e) { }
    		invalidate();
    		return;
    	}

    	// draws the unicorn at the specified point
		canvas.drawBitmap(unicorn.getImage(killed), unicorn.getX(), unicorn.getY(), null);
    	
		// draws the stroke
    	if (stroke.countPoints() > 1) {
    		for (int i = 0; i < stroke.countPoints() - 1; i++) {
    			int startX = stroke.getX(i);
    			int stopX = stroke.getX(i + 1);
    			int startY = stroke.getY(i);
    			int stopY = stroke.getY(i + 1);
    			Paint paint = new Paint();
    			paint.setColor(Stroke.getColor());
    			paint.setStrokeWidth(Stroke.getWidth());
    			canvas.drawLine(startX, startY, stopX, stopY, paint);
    		}
    	}
    	
    }

    /* 
     * This method is automatically called when the user touches the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    		stroke.addPoint((int)event.getX(), (int)event.getY());
    	}
    	else if (event.getAction() == MotionEvent.ACTION_MOVE) {
    		stroke.addPoint((int)event.getX(), (int)event.getY());
    	}
    	else if (event.getAction() == MotionEvent.ACTION_UP) {
    		stroke.clearPoints();
    	}
    	else {
    		return false;
    	}
    	
    	// see if the point is within the boundary of the image
    	Point touch = new Point((int)event.getX(), (int)event.getY());
    	// the !killed thing here is to prevent a "double-kill" that could occur
    	// while the "explosion" image is being shown
    	if (!killed && unicorn.isCollision(touch)) {
    		killed = true;
    		score++;
    		((TextView)(GameActivity.instance.getScoreboard())).setText(""+score);
    	}
    	
    	// forces a redraw of the View
    	invalidate();
    	
    	return true;
    }    

    
    /*
     * This inner class is responsible for making the unicorn appear to move.
     * When "exec" is called on an object of this class, "doInBackground" gets
     * called in a background thread. It just waits 10ms and then updates the
     * image's position. Then "onPostExecute" is called.
     */
    class BackgroundDrawingTask extends AsyncTask<Integer, Void, Integer> {
    	
    	// this method gets run in the background
    	protected Integer doInBackground(Integer... args) {
    		try { 
    			// note: you can change these values to make the unicorn go faster/slower
    			Thread.sleep(10);
    			unicorn.setX(unicorn.getX() + 10);
    			unicorn.setY(unicorn.getY() + yChange);
    		} 
    		catch (Exception e) { }
    		// the return value is passed to "onPostExecute" but isn't actually used here
    		return 1; 
    	}
    	
    	// this method gets run in the UI thread
    	protected void onPostExecute(Integer result) {
    		// redraw the View
    		invalidate();
    		if (score < 10) {
    			// need to start a new thread to make the unicorn keep moving
    			BackgroundDrawingTask task = new BackgroundDrawingTask();
    			task.execute();
    		}
    		else {
    			// game over, man!
    			endTime = System.currentTimeMillis();
    			// these methods are deprecated but it's okay to use them... probably.
    			GameActivity.instance.removeDialog(1);
    			GameActivity.instance.showDialog(1);
    		}
    	}    	
    }

}

