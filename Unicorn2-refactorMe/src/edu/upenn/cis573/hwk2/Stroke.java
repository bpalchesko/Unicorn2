package edu.upenn.cis573.hwk2;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Point;

public class Stroke {

	private ArrayList<Point> points;
	private static final int lineColor = Color.BLUE; //TODO Red
    private static final int lineWidth = 10;
    
    public Stroke() {
    	points = new ArrayList<Point>();
    }
    
    public int countPoints() {
    	return points.size();
    }
    
    public int getX(int pointIndex) {
    	return points.get(pointIndex).x;
    }
    
    public int getY(int pointIndex) {
    	return points.get(pointIndex).y;
    }
    
    public void addPoint(int x, int y) {
    	points.add(new Point(x,y));
    }
    
    public void clearPoints() {
    	points.clear();
    }
    
    public static int getColor() {
    	return lineColor;
    }
    
    public static int getWidth() {
    	return lineWidth;
    }
    
    
}
