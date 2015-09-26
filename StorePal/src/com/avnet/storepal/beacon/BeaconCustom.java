package com.avnet.storepal.beacon;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ImageView;

public class BeaconCustom {

	private int minor;
	private String section;
	private int nearRssi;
	private int medRssi;
	private int farRssi;
	private boolean seen;
	//Beacon Position 
	private PositionCustom beaconPos;
	// Each beacon has two relative positions
	private PositionCustom frontPos;
	private PositionCustom diagonalPos;
	private ImageView beaconMarker;
	private String promotion;
	private HashMap<String, Item> items;
	private ArrayList<double[]> routeFurther;
	private ArrayList<String> selectedProducts;
	
	public PositionCustom getBeaconPos() {
		return beaconPos;
	}
	public void setBeaconPos(PositionCustom beaconPos) {
		this.beaconPos = beaconPos;
	}
	public PositionCustom getFrontPos() {
		return frontPos;
	}
	public void setFrontPos(PositionCustom frontPos) {
		this.frontPos = frontPos;
	}
	public PositionCustom getDiagonalPos() {
		return diagonalPos;
	}
	public void setDiagonalPos(PositionCustom diagonalPos) {
		this.diagonalPos = diagonalPos;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getNearRssi() {
		return nearRssi;
	}
	public void setNearRssi(int nearRssi) {
		this.nearRssi = nearRssi;
	}
	public int getMedRssi() {
		return medRssi;
	}
	public void setMedRssi(int medRssi) {
		this.medRssi = medRssi;
	}
	public int getFarRssi() {
		return farRssi;
	}
	public void setFarRssi(int farRssi) {
		this.farRssi = farRssi;
	}
	public BeaconCustom(int nearRssi, int medRssi, int farRssi) {
		super();
		this.nearRssi = nearRssi;
		this.medRssi = medRssi;
		this.farRssi = farRssi;
	}
	public BeaconCustom() {
		super();
		this.selectedProducts = new ArrayList<String>();
		this.nearRssi = 65;
		this.medRssi = 75;
		this.farRssi = 90;
	}
	public HashMap<String, Item> getItems() {
		return items;
	}
	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}
	public ArrayList<double[]> getRouteFurther() {
		return routeFurther;
	}
	public void setRouteFurther(ArrayList<double[]> routeFurther) {
		this.routeFurther = routeFurther;
	}
	public ArrayList<String> getSelectedProducts() {
		return selectedProducts;
	}
	public void setSelectedProducts(ArrayList<String> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	public ImageView getBeaconMarker() {
		return beaconMarker;
	}
	public void setBeaconMarker(ImageView beaconMarker) {
		this.beaconMarker = beaconMarker;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
}
