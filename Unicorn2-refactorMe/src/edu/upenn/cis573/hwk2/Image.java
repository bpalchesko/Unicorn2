package edu.upenn.cis573.hwk2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.View;

public class Image {
	
	private Bitmap unicorn;
	private Bitmap explode;
	private Point imagePoint;
	
	public Image(Resources res) {
		imagePoint = new Point(-150,100);
		unicorn = BitmapFactory.decodeResource(res, R.drawable.unicorn);
		unicorn = Bitmap.createScaledBitmap(unicorn, 150, 150, false);
		explode = BitmapFactory.decodeResource(res, R.drawable.explosion);
	    explode = Bitmap.createScaledBitmap(explode, 150, 150, false);
	}
	
	public Bitmap getImage(boolean killed) {
		if (killed) {
			return explode;
		} else {
			return unicorn;
		}
	}
	
	public boolean isCollision(Point point) {
		return point.x > imagePoint.x && point.x < imagePoint.x + getWidth() 
				&& point.y > imagePoint.y && point.y < imagePoint.y + getHeight();
	}
	
	public int getX() {
		return imagePoint.x;
	}
	
	public int getY() {
		return imagePoint.y;
	}
	
	public void setX(int x) {
		imagePoint.x = x;
	}
	
	public void setY(int y) {
		imagePoint.y = y;
	}
	
	public int getWidth() {
		return unicorn.getWidth();
	}
	
	public int getHeight() {
		return unicorn.getHeight();
	}
	

}
