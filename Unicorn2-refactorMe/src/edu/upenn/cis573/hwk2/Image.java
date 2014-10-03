package edu.upenn.cis573.hwk2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Defines the image and location of the game's unicorn sprite.
 * @author Brad Palchesko
 *
 */
public class Image {

	private Bitmap unicorn;
	private Bitmap explode;
	private Point imagePoint;

	public Image(Resources res, int x, int y) {
		imagePoint = new Point(x, y);
		unicorn = BitmapFactory.decodeResource(res, R.drawable.unicorn);
		unicorn = Bitmap.createScaledBitmap(unicorn, 150, 150, false);
		explode = BitmapFactory.decodeResource(res, R.drawable.explosion);
		explode = Bitmap.createScaledBitmap(explode, 150, 150, false);
	}

	/**
	 * Yields the appropriate bitmap image given whether the unicorn is killed.
	 * @param killed
	 * @return bitmap image
	 */
	public Bitmap getImage(boolean killed) {
		if (killed) {
			return explode;
		} else {
			return unicorn;
		}
	}

	/**
	 * Determines whether a given point is within the bounds of the unicorn sprite.
	 * @param point
	 * @return true if within bounds
	 */
	public boolean isCollision(Point point) {
		return point.x > imagePoint.x && point.x < imagePoint.x + getWidth()
				&& point.y > imagePoint.y
				&& point.y < imagePoint.y + getHeight();
	}

	/**
	 * Yields the on-screen x-coordinate for this.
	 * @return x
	 */
	public int getX() {
		return imagePoint.x;
	}

	/**
	 * Yields the on-screen y-coordinate for this.
	 * @return y
	 */
	public int getY() {
		return imagePoint.y;
	}

	/**
	 * Sets the on-screen x-coordinate for this.
	 * @param x
	 */
	public void setX(int x) {
		imagePoint.x = x;
	}

	/**
	 * Yields the on-screen y-coordinate for this.
	 * @param y
	 */
	public void setY(int y) {
		imagePoint.y = y;
	}

	/**
	 * Yields the width of this, as defined by the unicorn bitmap image.
	 * @return width
	 */
	public int getWidth() {
		return unicorn.getWidth();
	}

	/**
	 * Yields the height of this, as defined by the unicorn bitmap image.
	 * @return height
	 */
	public int getHeight() {
		return unicorn.getHeight();
	}

}