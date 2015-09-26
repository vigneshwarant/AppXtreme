package com.avnet.storepal.beacon;

public class PositionCustom {

	private double x;
	private double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public PositionCustom(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public PositionCustom() {
		super();
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
