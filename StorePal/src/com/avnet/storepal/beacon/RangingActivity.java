package com.avnet.storepal.beacon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import android.app.Activity;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.avnet.storepal.KidFragment;
import com.avnet.storepal.R;
import com.avnet.storepal.constants.ListViewConstants;
import com.qozix.tileview.TileView;
import com.qozix.tileview.hotspots.HotSpot;
import com.qozix.tileview.hotspots.HotSpotEventListener;
import com.qozix.tileview.markers.MarkerEventListener;
import com.qozix.tileview.paths.DrawablePath;

public class RangingActivity extends Activity implements BeaconConsumer{
    protected static final String TAG = "RangingActivity";
    
    HashMap<String, Item> itemsMap = new HashMap<String, Item>();
    BeaconCustom currentlyNearBeacon;
    DrawablePath currentPath;
    RangingActivity rangingActivity;
    private static final boolean isMSBay = true;
    //FIXME : Set Start Point here
    //This is for PSM Bay
	/*private static final PositionCustom STARTPOSITION = new PositionCustom(1703.00, 2440.00);
	//FIXME : Set END Point here
	private static final PositionCustom ENDPOSITION = new PositionCustom(1703.00, 675.00);
	private static final PositionCustom BILLINGPOSITION = new PositionCustom(1970, 675);*/
	//This is for MS Bay
	private static final PositionCustom STARTPOSITION = new PositionCustom(364.00, 2400.00);
	//FIXME : Set END Point here
	private static final PositionCustom ENDPOSITION = new PositionCustom(364.00, 685.00);
	private static final PositionCustom BILLINGPOSITION = new PositionCustom(185.00, 685.00);
	
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
    TileView tileView;
    ImageView markerA, markerB;
    int nearBeacId[] = new int[5];
    int small = 999, nearestBeaconId = 0, goRound = 0;
 // All 27 Points
    private ArrayList<double[]> points = new ArrayList<double[]>();
   
	/*{
		points.add( new double[] { 1703, 2320}); //0
		points.add( new double[] { 1432, 2320 }); // 1
		points.add( new double[] { 1703, 1746 }); // 2
		points.add( new double[] { 1432, 1746 }); //3
		points.add( new double[] { 1703, 950 }); //4
		points.add( new double[] { 1432, 950 }); //5 
		points.add( new double[] { 1703, 385 }); //6
		points.add( new double[] { 1432, 385}); //7 
		points.add( new double[] { 608, 2320 }); //8
		points.add( new double[] { 314, 2320 }); //9
		points.add( new double[] { 608, 1746 }); //10
		points.add( new double[] { 314, 1746 }); //11
		points.add( new double[] { 608, 950 }); //12
		points.add( new double[] { 314, 950 }); //13
		points.add( new double[] { 608, 385 }); //14
		points.add( new double[] { 314, 385 }); //15
		/*points.add( new double[] { 1440, 667}); //16
		points.add( new double[] { 746, 667}); //17
		points.add( new double[] { 1970, 477});//18
		points.add( new double[] { 1725, 477});//19
		points.add( new double[] { 1440, 477});//20
		points.add( new double[] { 1080, 477});//21
		points.add( new double[] { 746, 477});//22
		points.add( new double[] { 440, 477});//23
		points.add( new double[] { 1970, 249});//24
		points.add( new double[] { 1440, 249});//25
		points.add( new double[] { 746, 249});//26
	
	}*/
//	private ArrayList<double[]> points = new ArrayList<double[]>();
	 // MS Bay  
	{
		points.add( new double[] { 364, 2335}); //0
		points.add( new double[] { 635, 2335 }); // 1
		points.add( new double[] { 364, 1742 }); // 2
		points.add( new double[] { 635, 1742 }); //3
		points.add( new double[] { 364, 955 }); //4
		points.add( new double[] { 635, 955 }); //5 
		points.add( new double[] { 364, 385 }); //6
		points.add( new double[] { 635, 385}); //7 
		points.add( new double[] { 1484, 2335 }); //8
		points.add( new double[] { 1758, 2335 }); //9
		points.add( new double[] { 1484, 1742 }); //10
		points.add( new double[] { 1758, 1742 }); //11
		points.add( new double[] { 1484, 955 }); //12
		points.add( new double[] { 1758, 955 }); //13
		points.add( new double[] { 1484, 385 }); //14
		points.add( new double[] { 1758, 385 }); //15
		/*points.add( new double[] { 1440, 667}); //16
		points.add( new double[] { 746, 667}); //17
		points.add( new double[] { 1970, 477});//18
		points.add( new double[] { 1725, 477});//19
		points.add( new double[] { 1440, 477});//20
		points.add( new double[] { 1080, 477});//21
		points.add( new double[] { 746, 477});//22
		points.add( new double[] { 440, 477});//23
		points.add( new double[] { 1970, 249});//24
		points.add( new double[] { 1440, 249});//25
		points.add( new double[] { 746, 249});//26
*/		
	}
    
	
	HashMap<Integer, BeaconCustom> myBeacons = instantiateBeacons();
    
	private MarkerEventListener markerEventListener = new MarkerEventListener() {
		@Override
		public void onMarkerTap( View v, int x, int y ) {
			Toast.makeText( getApplicationContext(), "You tapped a pin", Toast.LENGTH_LONG ).show();
		}		
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tileView = new TileView(this);
        rangingActivity = this;
		tileView.setSize(2117,2582);
		if(!isMSBay){
        tileView.addDetailLevel(1f, "tiles/1000_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.5f, "tiles/500_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.25f, "tiles/250_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.125f, "tiles/125_%col%_%row%.png", "downsamples/map.png");
		}
		else{
        tileView.addDetailLevel(1f, "qabay/tiles/1000_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.5f, "qabay/tiles/500_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.25f, "qabay/tiles/250_%col%_%row%.png", "downsamples/map.png");
        tileView.addDetailLevel(0.125f, "qabay/tiles/125_%col%_%row%.png", "downsamples/map.png");
		}
        
        //tileView.setScale( 0.25 );
        
        tileView.defineRelativeBounds(0, 0, 2116, 2582);
        
        
        markerA = new ImageView(this);
        tileView.addMarker(markerA, 1703, 2320, -0.5f, -1.0f);
        markerA.setImageResource(R.drawable.manmarker);
        markerA.setTag("You are here");
        
        markerB = new ImageView(this);
        tileView.addMarker(markerB, 3000, 3000, -0.5f, -1.0f);
        markerB.setImageResource(R.drawable.destmarker);
        markerB.setTag("You are here");
        
        tileView.slideToAndCenter( 1703, 2320 );
        
        Paint paint = tileView.getPathPaint();
		paint.setShadowLayer( 4, 2, 2, 0x66000000 );
		paint.setPathEffect( new CornerPathEffect( 5 ) );
		
		 BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1)getApplication();
		 String listName = applicationObj.listName;
		
		ArrayList<String> products = new ArrayList<String>();
		/*products.add("Lego Toys");
		products.add("Lays chips");
		products.add("Basketball");
		products.add("Digital Fortress");
		products.add("Parker Pen");
		products.add("Bose earphones");
		products.add("Mens shirt");
		products.add("Hammer");
		*/
		// Winter Utility Products 
		//  CALL THIS FUNCTION TO ADD WINTER PRODUCTS. I HAVE ADDED WINTER PRODUCTS TO THE LIST.
		//TODO Determine what product need to be showed
		if(listName.equalsIgnoreCase(ListViewConstants.WINTER_LIST)){
			addWinterUtilityProducts(products);
		}
		//TODO Add Necessary Items
		else if(listName.equalsIgnoreCase(ListViewConstants.KIDS_LIST)){
			addKids(products);
		}
		else if(listName.equalsIgnoreCase(ListViewConstants.WISH_LIST)){
			addKids(products);
		}
		//FIXME FALBBACK TO Winter Products 
		else{
		addWinterUtilityProducts(products);
		}
		
		createPathsWithProducts(products);
        
		//tileView.addMarkerEventListener( markerEventListener );
		
        setContentView(tileView);
		//setContentView(R.layout.activity_ranging);
        beaconManager.bind(this);
    }
    
    
    private void createPathsWithProducts(ArrayList<String> products){
    	ArrayList<BeaconCustom> beaconsWithProducts = new ArrayList<BeaconCustom>();
    	for (String product : products) {
			if(itemsMap.containsKey(product)){
				ArrayList<String> selectedProducts = myBeacons.get(itemsMap.get(product).getSectionId()).getSelectedProducts();
				selectedProducts.add(product);
				beaconsWithProducts.add(myBeacons.get(itemsMap.get(product).getSectionId()));
				
			}
		}
    	
    	ArrayList<BeaconCustom> beaconsWithProductsSorted = new ArrayList<BeaconCustom>();
    	beaconsWithProductsSorted = sortProductListRecursive(STARTPOSITION, beaconsWithProducts, beaconsWithProductsSorted);
		ArrayList<PositionCustom> pointsList = startPloter(beaconsWithProductsSorted.get(0));
		ArrayList<double[]> points = points(pointsList);
		myBeacons.get(9).setRouteFurther(points);
		//DrawablePath startToFirstPath = tileView.drawPath(points.subList( 0, points.size() ));
		
		
		//tileView.removePath(startToFirstPath);
		if(beaconsWithProductsSorted.size()<2){
			pointsList = endPloter(beaconsWithProductsSorted.get(0));
			points = points(pointsList);
			myBeacons.get(beaconsWithProductsSorted.get(0).getMinor()).setRouteFurther(points);
			Log.i("RangingActivity", "Setting routeFurther for beacon = "+beaconsWithProductsSorted.get(0).getMinor());
			//tileView.drawPath(points.subList( 0, points.size() ));
		}
		else{
			for(int i =0;i<beaconsWithProductsSorted.size()-1;i++){
				pointsList = pointPloter(beaconsWithProductsSorted.get(i), beaconsWithProductsSorted.get(i+1));
				points = points(pointsList);
				myBeacons.get(beaconsWithProductsSorted.get(i).getMinor()).setRouteFurther(points);
				Log.i("RangingActivity", "Setting routeFurther for beacon = "+beaconsWithProductsSorted.get(i).getMinor());
				
				//tileView.drawPath(points.subList( 0, points.size() ));
			}
			pointsList = endPloter(beaconsWithProductsSorted.get(beaconsWithProductsSorted.size()-1));
			points = points(pointsList);
			myBeacons.get(beaconsWithProductsSorted.get(beaconsWithProductsSorted.size()-1).getMinor()).setRouteFurther(points);
		}
		
		for(BeaconCustom beaconCustom : beaconsWithProductsSorted){
			ImageView beaconMarker = new ImageView(this);
	        tileView.addMarker(beaconMarker, beaconCustom.getFrontPos().getX()+60.0, beaconCustom.getFrontPos().getY()+25.0, -0.5f, -1.0f);
	        beaconMarker.setImageResource(R.drawable.todo);
	        beaconCustom.setBeaconMarker(beaconMarker);
	        final double calloutXPos = beaconCustom.getFrontPos().getX()+60.0;
	        final double calloutYPos = beaconCustom.getFrontPos().getY()+25.0;
	        final ArrayList<String> selectedProducts = beaconCustom.getSelectedProducts();
	        beaconMarker.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SampleCallout callout = new SampleCallout( v.getContext(), selectedProducts);
					// add it to the view tree at the same position and offset as the marker that invoked it
					tileView.addCallout( callout,calloutXPos , calloutYPos, -0.5f, -1.0f );
					// a little sugar
					callout.transitionIn();	
				}
			});
		}
    }
    
	
    
    /**
     * Function to Instantiate all Beacons
     * @return
     * 
     * TV has added 3 Points to BeaconCustom Object : Front Diagonal Side
     * Not Removing MV's near far med points as of now, though it is not useful
     */
    private HashMap<Integer, BeaconCustom> instantiateBeacons() {
		HashMap<Integer,BeaconCustom> beacons = new HashMap<Integer, BeaconCustom>();
		
		BeaconCustom beacon = new BeaconCustom();
		//First Beacon
		beacon.setMinor(1);
		beacon.setSection("Toys");
		beacon.setDiagonalPos(new PositionCustom( points.get(0)[0],points.get(0)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(1)[0],points.get(1)[1]));
		if(!isMSBay){
		beacon.setBeaconPos(new PositionCustom(1432,2174));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(635,2177));
		}
		HashMap<String, Item> toysBeaconItems = new HashMap<String, Item>();
		Item item = new Item("Lego Toys", "50", "Buy 2 sets for $75","Reviews", 4);
		item.setSectionId(1);
		itemsMap.put(item.getItemName(), item);
		toysBeaconItems.put("Lego Set", item);
		item = new Item("Duckling", "50", "Buy 2 set get one free","Reviews", 4);
		item.setSectionId(1);
		itemsMap.put(item.getItemName(), item);
		toysBeaconItems.put("Duckling", item);
		item = new Item("Ballon", "10", "","Reviews", 4);
		item.setSectionId(1);
		itemsMap.put(item.getItemName(), item);
		toysBeaconItems.put("Ballon", item);
		beacon.setItems(toysBeaconItems);
		beacon.setPromotion("Buy 2 lego sets for $75");
		beacons.put(1, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(2);
		beacon.setSection("Food");
		beacon.setDiagonalPos(new PositionCustom( points.get(2)[0],points.get(2)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(3)[0],points.get(3)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(1432,1589));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(635,1593));
		}
		HashMap<String, Item> foodBeaconItems = new HashMap<String, Item>();
		item = new Item("Lays chips", "6", "Pringle stack for $4","Reviews", 5);
		item.setSectionId(2);
		itemsMap.put(item.getItemName(), item);
		foodBeaconItems.put("Lays chips", item);
		item = new Item("Burger", "3", "Get Extra Cheese","Reviews", 5);
		item.setSectionId(2);
		itemsMap.put(item.getItemName(), item);
		foodBeaconItems.put("Burger", item);
		beacon.setItems(foodBeaconItems);
		beacon.setPromotion("Pringle stack for $4");
		beacons.put(2, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(3);
		beacon.setSection("Sports");
		beacon.setDiagonalPos(new PositionCustom( points.get(4)[0],points.get(4)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(5)[0],points.get(5)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(1432,790));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(635,798));
		}
		HashMap<String, Item> sportsBeaconItems = new HashMap<String, Item>();
		item = new Item("Basketball", "30", "Buy basketball net for $20","Reviews", 5);
		item.setSectionId(3);
		itemsMap.put(item.getItemName(), item);
		sportsBeaconItems.put("Basketball", item);
		item = new Item("Ice Spike", "$55", "Buy 2 pairs, get 3rd for 10% off","Ice spike Reviews", 5);
		item.setSectionId(3);
		itemsMap.put(item.getItemName(), item);
		sportsBeaconItems.put("Ice Spike", item);
		beacon.setItems(sportsBeaconItems);
		beacon.setPromotion("Buy basketball net for $20");
		beacons.put(3, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(4);
		beacon.setSection("Books");
		beacon.setDiagonalPos(new PositionCustom( points.get(6)[0],points.get(6)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(7)[0],points.get(7)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(1432,247));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(635,248));
		}
		HashMap<String, Item> booksBeaconItems = new HashMap<String, Item>();
		item = new Item("Digital Fortress", "20", "5 books for $10 each","Reviews", 5);
		item.setSectionId(4);
		itemsMap.put(item.getItemName(), item);
		booksBeaconItems.put("Digital Fortress", item);
		item = new Item("Intersteller", "20", "5 books for $10 each","Reviews", 5);
		item.setSectionId(4);
		itemsMap.put(item.getItemName(), item);
		booksBeaconItems.put("Intersteller", item);
		item = new Item("The Immortals of Meluha", "20", "5 books for $10 each","Reviews", 5);
		item.setSectionId(4);
		itemsMap.put(item.getItemName(), item);
		booksBeaconItems.put("The Immortals of Meluha", item);
		beacon.setItems(booksBeaconItems);
		beacon.setPromotion("5 books for $ 10 each");
		beacons.put(4, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(5);
		beacon.setSection("Stationery");
		beacon.setDiagonalPos(new PositionCustom( points.get(8)[0],points.get(8)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(9)[0],points.get(9)[1]));
		if(!isMSBay){
		beacon.setBeaconPos(new PositionCustom(314,2174));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(1758,2177));
		}
		HashMap<String, Item> stationeryBeaconItems = new HashMap<String, Item>();
		item = new Item("Parker Pen", "5", "Buy parker bundle set at $9.99 each","Reviews", 5);
		item.setSectionId(5);
		itemsMap.put(item.getItemName(), item);
		stationeryBeaconItems.put("Parker Pen", item);
		item = new Item("Utility Rope", "$99", "Utility Rope buy one get one free","Best Quality Rope", 5);
		item.setSectionId(5);
		itemsMap.put(item.getItemName(), item);
		stationeryBeaconItems.put("Utility Rope", item);
		beacon.setItems(stationeryBeaconItems);
		beacon.setPromotion("Buy parker bundle set at $9.99 each");
		beacons.put(5, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(6);
		beacon.setSection("Electronics");
		beacon.setDiagonalPos(new PositionCustom( points.get(10)[0],points.get(10)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(11)[0],points.get(11)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(314,1589));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(1758,1593));
		}
		HashMap<String, Item> electronicsBeaconItems = new HashMap<String, Item>();
		item = new Item("Bose earphones", "199", "Mobiles at 50% discount","Reviews", 5);
		item.setSectionId(6);
		itemsMap.put(item.getItemName(), item);
		electronicsBeaconItems.put("Bose earphones", item);
		item = new Item("Car Batteries", "$80", "","Reviews", 5);
		item.setSectionId(6);
		itemsMap.put(item.getItemName(), item);
		electronicsBeaconItems.put("Car Batteries", item);
		item = new Item("IPhone 6s", "$699", "Mobiles at 50% discount","Reviews", 5);
		item.setSectionId(6);
		itemsMap.put(item.getItemName(), item);
		electronicsBeaconItems.put("IPhone 6s", item);
		item = new Item("sterliser", "$89", "","Reviews", 5);
		item.setSectionId(6);
		itemsMap.put(item.getItemName(), item);
		electronicsBeaconItems.put("sterliser", item);
		beacon.setItems(electronicsBeaconItems);
		beacon.setPromotion("Mobiles at 50% discount");
		beacons.put(6, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(7);
		beacon.setSection("Clothing");
		beacon.setDiagonalPos(new PositionCustom( points.get(12)[0],points.get(12)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(13)[0],points.get(13)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(314,790));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(1758,798));
		}
		HashMap<String, Item> clothingBeaconItems = new HashMap<String, Item>();
		item = new Item("Mens shirt", "30", "Buy a complete suit for $80","Reviews", 5);
		item.setSectionId(7);
		itemsMap.put(item.getItemName(), item);
		clothingBeaconItems.put("Mens shirt", item);
		item = new Item("Winter jackets", "$ 69", "","Winter jackets are very good", 5);
		item.setSectionId(7);
		itemsMap.put(item.getItemName(), item);
		clothingBeaconItems.put("Winter jackets", item);
		item = new Item("Blanket", "$35", "","Blanket helps in keeping us warm", 5);
		item.setSectionId(7);
		itemsMap.put(item.getItemName(), item);
		clothingBeaconItems.put("Blanket", item);
		item = new Item("Nike Shoe", "$35", "","Blanket helps in keeping us warm", 5);
		item.setSectionId(7);
		itemsMap.put(item.getItemName(), item);
		clothingBeaconItems.put("Nike Shoe", item);
		beacon.setItems(clothingBeaconItems);
		beacon.setPromotion("Buy a complete suit for $80");
		beacons.put(7, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(8);
		beacon.setSection("Hardware");
		beacon.setDiagonalPos(new PositionCustom( points.get(14)[0],points.get(14)[1]));
		beacon.setFrontPos(new PositionCustom( points.get(15)[0],points.get(15)[1]));
		if(!isMSBay){
			beacon.setBeaconPos(new PositionCustom(314,247));
		}
		else{
			beacon.setBeaconPos(new PositionCustom(1758,248));
		}
		HashMap<String, Item> hardwareBeaconItems = new HashMap<String, Item>();
		item = new Item("Hammer", "10", "Scredriver sets at 40% off","Reviews", 5);
		item.setSectionId(8);
		itemsMap.put(item.getItemName(), item);
		hardwareBeaconItems.put("Hammer", item);
		item = new Item("Multi-Function Tool Knife", "$99", "Screwdriver sets at 40% off","Reviews", 5);
		item.setSectionId(8);
		itemsMap.put(item.getItemName(), item);
		hardwareBeaconItems.put("Multi-Function Tool Knife", item);
		beacon.setItems(hardwareBeaconItems);
		beacon.setPromotion("Screwdriver sets at 40% off");
		beacons.put(8, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(9);
		beacon.setSection("Start");
		beacon.setDiagonalPos(STARTPOSITION);
		beacon.setFrontPos(STARTPOSITION);
		beacon.setBeaconPos(STARTPOSITION);
		beacon.setPromotion("Welcome to Avnet Stores");
		beacons.put(9, beacon);
		
		beacon = new BeaconCustom();
		beacon.setMinor(10);
		beacon.setSection("End");
		beacon.setDiagonalPos(ENDPOSITION);
		beacon.setFrontPos(ENDPOSITION);
		beacon.setBeaconPos(ENDPOSITION);
		beacon.setPromotion("Thank you for shopping");
		beacons.put(10, beacon);
		
		return beacons;
	}
	@Override 
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    @Override 
    protected void onPause() {
    	super.onPause();
    	if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }
    @Override 
    protected void onResume() {
    	super.onResume();
    	if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
    }
    
    private void moveMarkerToPosition(final PositionCustom positionCustom) {
    	
    	runOnUiThread(new Runnable() {
    	     @Override
    	     public void run() {
    	 			tileView.moveMarker(markerA, positionCustom.getX(), positionCustom.getY(), -0.5f, -1.0f);
    	 			
    	 			
    	 			ArrayList<double[]> pathPoints = currentlyNearBeacon.getRouteFurther();
	 			
    	 			if(pathPoints!=null && pathPoints.size()>0){
    	 				if(currentPath!=null)
        	 				tileView.removePath(currentPath);
    	 				currentPath = tileView.drawPath(pathPoints.subList( 0, pathPoints.size() ));
    	 				tileView.moveMarker(markerB, pathPoints.get(pathPoints.size()-1)[0], pathPoints.get(pathPoints.size()-1)[1],-0.5f, -1.0f);
    	 			}
    	 			
    	 			if(!currentlyNearBeacon.isSeen()){
    	 				new VibratingToast(getApplicationContext(),rangingActivity, currentlyNearBeacon.getPromotion(), currentlyNearBeacon.getSection(),5000, null);
    	 				tileView.slideToAndCenter( positionCustom.getX(), positionCustom.getY() );
    	 				currentlyNearBeacon.setRouteFurther(null);
    	 				currentlyNearBeacon.setSeen(true);
    	 			}
    	     }
    	});
			
	}
    
    Comparator<Beacon> comparator = new Comparator<Beacon>() {
        public int compare(Beacon b1, Beacon b2) {
            return (b1.getRssi()*-1) - (b2.getRssi()*-1); // use your logic
        }
    };
    Comparator<Beacon> myDistanceComparator = new Comparator<Beacon>() {
        public int compare(Beacon b1, Beacon b2) {
            return ((int)(b1.getDistance()*1000) - (int)(b2.getDistance()*1000)); // use your logic
        }
    };
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
        @Override 
        public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
            if (beacons.size() > 0) {
            
            	List<Beacon> beaconsList = new ArrayList(beacons);
        		Collections.sort(beaconsList, comparator);
        		nearestBeaconId = beaconsList.get(0).getId3().toInt();
        		
        		nearBeacId[goRound] = nearestBeaconId;
        		Log.i("RangingActivity", "runningNear = "+nearestBeaconId);
        		
        		
        		goRound++;
        		
        		if(goRound == 5){
        			BeaconCustom nearestBeacon = myBeacons.get(findMaxOccurence(nearBeacId));
        			if(nearestBeacon!=null){
            			Log.i("RangingActivity", "Actually near Beacon = "+nearestBeacon.getMinor());
            			currentlyNearBeacon = nearestBeacon;
            			PositionCustom positionCustom = new PositionCustom();
                		positionCustom = nearestBeacon.getFrontPos();
            			moveMarkerToPosition(positionCustom);
        			}
        			goRound = 0;
        		}
        		
        }
    }

	private int findMaxOccurence(int[] a) {
		int count = 1, tempCount;
		  int popular = a[0];
		  int temp = 0;
		  for (int i = 0; i < (a.length - 1); i++)
		  {
		    temp = a[i];
		    tempCount = 0;
		    for (int j = 1; j < a.length; j++)
		    {
		      if (temp == a[j])
		        tempCount++;
		    }
		    if (tempCount > count)
		    {
		      popular = temp;
		      count = tempCount;
		    }
		  }
		  return popular;
		
	}

        });

        try {
        	String uuid = "12345678-9012-3456-7890-123456789012";
        	beaconManager.setForegroundScanPeriod(300);
        	beaconManager.setForegroundBetweenScanPeriod(0);
        	beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", Identifier.parse(uuid), null, null));
        } 
           catch (RemoteException e) {   }
    }
    
    
		/******************************** This function set is used to plot points *******************************/
		
		/**
		 * Function to start the plot from Home position
		 * @param destination
		 * @return
		 */
		
		private ArrayList<PositionCustom> startPloter(BeaconCustom destination){
			
			ArrayList<PositionCustom> plotPoints = new ArrayList<PositionCustom>();
			if(destination.getFrontPos().getY()== STARTPOSITION.getY()){
				plotPoints.add(STARTPOSITION);
				plotPoints.add(destination.getFrontPos());
				return plotPoints; 
			}
			else {
				plotPoints.add(STARTPOSITION);
				double Y = destination.getDiagonalPos().getY();
				double X =STARTPOSITION.getX();
				PositionCustom  tempPoint = new PositionCustom(X,Y);
				plotPoints.add(tempPoint);
				plotPoints.add(destination.getFrontPos());

			}
				
			return plotPoints;
		}
		
		/**
		 * Function to start the plot from any product beacon to another product beacon
		 * @param destination
		 * @return
		 */
		private ArrayList<PositionCustom> pointPloter(BeaconCustom source, BeaconCustom destination) {
			
			ArrayList<PositionCustom> plotPoints = new ArrayList<PositionCustom>();
			if(source.getFrontPos().getY() == destination.getFrontPos().getY()){
				plotPoints.add(source.getFrontPos());
				plotPoints.add(destination.getFrontPos());
				return plotPoints; 
			}
			else{
				plotPoints.add(source.getFrontPos());
				plotPoints.add(source.getDiagonalPos());
				double Y = destination.getDiagonalPos().getY();
				double X = source.getDiagonalPos().getX();
				PositionCustom  tempPoint = new PositionCustom(X,Y);
				plotPoints.add(tempPoint);
				plotPoints.add(destination.getFrontPos());
				
			}
			return plotPoints;
			
		}
		
		/**
		 * Function to plot between last product and exit
		 * @param destination
		 * @return
		 */
		private ArrayList<PositionCustom> endPloter(BeaconCustom source){
			
			ArrayList<PositionCustom> plotPoints = new ArrayList<PositionCustom>();
			if(source.getFrontPos().getY()== ENDPOSITION.getY()){
				plotPoints.add(source.getFrontPos());
				plotPoints.add(ENDPOSITION);
				return plotPoints; 
			}
			else {
				plotPoints.add(source.getFrontPos());
				plotPoints.add(source.getDiagonalPos());
				double Y = ENDPOSITION.getY();
				double X =source.getDiagonalPos().getX();
				PositionCustom  tempPoint = new PositionCustom(X,Y);
				plotPoints.add(tempPoint);
				plotPoints.add(ENDPOSITION);
				plotPoints.add(BILLINGPOSITION);

			}
				
			return plotPoints;
		}
		
		/************************************************ End of Plotting Function ***************************/
		
		
		/**************************** Sort Function to sort the order of products **************************************/
		
	/*	private ArrayList<BeaconCustom> sortProductList( ArrayList<BeaconCustom> beaconCustomsList) {
			
			BeaconCustom startPosition = new BeaconCustom();
			startPosition.setFrontPos(STARTPOSITION);
			beaconCustomsList.add(startPosition);
			double distance[] = new double[beaconCustomsList.size()] ;
			for(int i=0;i<beaconCustomsList.size();i++){
				distance[i] = calculateDistance(STARTPOSITION, beaconCustomsList.get(i).getFrontPos());
			}
			int index = 0;
			for (int i = 1; i < distance.length; i++) {
				if(distance[i] < distance[index])
                    index = i;
			}
			
			
			ArrayList<BeaconCustom> beaconCustomsListSorted= new ArrayList<BeaconCustom>();
			beaconCustomsListSorted.add(beaconCustomsList.get(index));
			int itemSize = beaconCustomsList.size();
			for(int i=0;i<itemSize;i++){
				for(int j=i; j>0 ;j--){
					//if()
				}
				
			}
			double leastDistance = calculateDistance(STARTPOSITION, beaconCustomsList.get(0).getFrontPos());
			//while(){
				
			//}
			
		}*/
		
		
		// Using Recusrive call
		/**
		 * This function is used to sort the given Product list. Pass StartPosition Constant, Product List mapped to BeaconCustom List
		 * @param position
		 * @param beaconCustomsList
		 * @param beaconCustomsListSorted
		 * @return
		 */
			private ArrayList<BeaconCustom> sortProductListRecursive( PositionCustom position, ArrayList<BeaconCustom> beaconCustomsList, ArrayList<BeaconCustom> beaconCustomsListSorted ) {
			
				// Recursive Stoppage Condition
			if(beaconCustomsList.size() == 1){
				beaconCustomsListSorted.add(beaconCustomsList.get(0));
				return beaconCustomsListSorted;
			}
			double distance[] = new double[beaconCustomsList.size()] ;
			for(int i=0;i<beaconCustomsList.size();i++){
				distance[i] = calculateDistance(position, beaconCustomsList.get(i).getFrontPos());
			}
			int index = 0;
			for (int i = 1; i < distance.length; i++) {
				//Log.i("Ranging Activity","distance from current Position X: "+ position.getX() +"Y: " + position.getY() + " Distance is : "+distance[i]  );
				if(distance[i] < distance[index])
                    index = i;
			}
			beaconCustomsListSorted.add(beaconCustomsList.get(index));
			beaconCustomsList.remove(index);
			return sortProductListRecursive(beaconCustomsListSorted.get(beaconCustomsListSorted.size()-1).getFrontPos(), beaconCustomsList, beaconCustomsListSorted);
			
		}


		
		
		/**************************** Sort Function Ends here to sort the order of products **************************************/
		
		/**************************** Utility functions **************************************/
			
		/**
	    * Find the distance b/w co-ordinates
		* @param point1
		* @param point2
		* @return
		*/
		private double calculateDistance(PositionCustom point1,
				PositionCustom point2 ) {
			// TODO Auto-generated method stub
			return Math.sqrt(Math.pow(point1.getX()-point2.getX(),2) + Math.pow(point1.getY()-point2.getY(),2));
		}
		/**
		 * To Get Custom plots for drawing points
		 * @param positionCustoms
		 * @return
		 */
		private ArrayList<double[]> points( ArrayList<PositionCustom> positionCustoms){
			ArrayList<double[]> points = new ArrayList<double[]>();
			int  size = positionCustoms.size();
			for(int i=0; i<size ; i++){
				points.add( new double[] { positionCustoms.get(i).getX(), positionCustoms.get(i).getY()}); //0
			}
			return points;
			
		}
		/**
		 * To get Winter Object
		 * @param products
		 */
		private void addWinterUtilityProducts(ArrayList<String> products) {
		products.add("Blanket");
		products.add("Car Batteries");
		products.add("Winter jackets");
		products.add("Ice Spike");
		products.add("Multi-Function Tool Knife");
		products.add("Utility Rope");
		}
		/**
		 * 
		 * @param products
		 */
		private void addKids(ArrayList<String> products) {
			products.add("sterliser");
			products.add("Duckling");
			products.add("Intersteller");
			products.add("Ballon");
			}
		/**
		 * Wish List
		 * 
		 * @param products
		 */
		private void wishList(ArrayList<String> products) {
			products.add("Burger");
			products.add("IPhone 6s");
			products.add("Nike Shoe");
			products.add("The Immortals of Meluha");
			}
		

}