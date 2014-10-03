package edu.upenn.cis573.hwk2;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Defines the line produced by the player's swipe on the screen.
 * @author Brad Palchesko
 *
 */
public class Stroke {

	private ArrayList<Point> points;
	private static final int lineColor = Color.RED;
    private static final int lineWidth = 10;
    
    public Stroke() {
    	points = new ArrayList<Point>();
    }
    
    /**
     * Yields the number of coordinate points defining the stroke.
     * @return point quantity
     */
    public int countPoints() {
    	return points.size();
    }
    
    /**
     * Yields the x-coordinate of the point in the sequential position specified.
     * @param pointIndex
     * @return x-coordinate
     */
    public int getX(int pointIndex) {
    	return points.get(pointIndex).x;
    }
    
    /**
     * Yields the y-coordinate of the point in the sequential position specified.
     * @param pointIndex
     * @return y-coordinate
     */
    public int getY(int pointIndex) {
    	return points.get(pointIndex).y;
    }
    
    /**
     * Adds the coordinates of a point to the sequence of points defining the stroke.
     * @param x
     * @param y
     */
    public void addPoint(int x, int y) {
    	points.add(new Point(x,y));
    }
    
    /**
     * Clears the sequence of points.
     */
    public void clearPoints() {
    	points.clear();
    }
    
    /**
     * Yields the color of a Stroke as drawn on the scren.
     * @return color
     */
    public static int getColor() {
    	return lineColor;
    }
    
    /**
     * Yields the width of a Stroke as drawn on the screen.
     * @return width in pixels
     */
    public static int getWidth() {
    	return lineWidth;
    }
       
}
