package com.avnet.storepal.data;

import java.util.ArrayList;

import com.avnet.storepal.R;
import com.avnet.storepal.beans.ProductListViewBean;

public class ProductList {
	
	/**
	 * Show me best options 
	 * 
	 */
	
	 public static ArrayList<ProductListViewBean> getMyWishList(){
		 
		 
		 ArrayList<ProductListViewBean> productList = new ArrayList<ProductListViewBean>();
		 ProductListViewBean item1 = new ProductListViewBean();
		   
		   item1.setAvailableQty("5 pcs");
		   item1.setBrandName("McDonalds");
		   item1.setImageName(R.drawable.burger);
		   item1.setPrice("$13");
		   item1.setProductName("Burger");
		   item1.setProductShortDesc("Special Veg Burger for you");
		   
		   productList.add(item1);
		   
		   ProductListViewBean item2 = new ProductListViewBean();
		   item2.setAvailableQty("24 pcs");
		   item2.setBrandName("Apple");
		   item2.setImageName(R.drawable.iphone5);
		   item2.setPrice("$699");
		   item2.setProductName("IPhone 6s");
		   item2.setProductShortDesc("If you Don't own a IPhone, you don't own a phone");
		   productList.add(item2);
		   
		   ProductListViewBean item3 = new ProductListViewBean();
		   item3.setAvailableQty("30 pcs");
		   item3.setBrandName("Nike");
		   item3.setImageName(R.drawable.shoe);
		   item3.setPrice("$239");
		   item3.setProductName("Nike Shoe");
		   item3.setProductShortDesc("Stylish Sport Shoe, which goes well with your Jeans ");
		   productList.add(item3);
		   
		   ProductListViewBean item4 = new ProductListViewBean();
		   item4.setAvailableQty("20 pcs");
		   item4.setBrandName("Amish");
		   item4.setImageName(R.drawable.meluha);
		   item4.setPrice("$39");
		   item4.setProductName("The Immortals of Meluha");
		   item4.setProductShortDesc("The next part in your collection");
		   productList.add(item4);
		   
		   return productList;
	 }
	/**
	 * Get Kids List
	 * 
	 * @return
	 */
	 public static ArrayList<ProductListViewBean> getKidList(){
		 
		 ArrayList<ProductListViewBean> productList = new ArrayList<ProductListViewBean>();
		   
		   ProductListViewBean item1 = new ProductListViewBean();
		   
		   item1.setAvailableQty("10 pcs");
		   item1.setBrandName("Resun");
		   item1.setImageName(R.drawable.sterliser);
		   item1.setPrice("$35");
		   item1.setProductName("sterliser");
		   item1.setProductShortDesc("To sterlize kids' feeding bottle ");
		   
		   productList.add(item1);
		   
		   ProductListViewBean item2 = new ProductListViewBean();
		   item2.setAvailableQty("10 pcs");
		   item2.setBrandName("Funskool");
		   item2.setImageName(R.drawable.batteries);
		   item2.setPrice("$14");
		   item2.setProductName("Duckling");
		   item2.setProductShortDesc("Soft Toy for Kids 5+");
		   productList.add(item2);
		   
		   ProductListViewBean item3 = new ProductListViewBean();
		   item3.setAvailableQty("30 pcs");
		   item3.setBrandName("Nolan Series");
		   item3.setImageName(R.drawable.intersteller);
		   item3.setPrice("$19");
		   item3.setProductName("Intersteller");
		   item3.setProductShortDesc("Intersteller is not a rocket science when you have this");
		   productList.add(item3);
		   
		   ProductListViewBean item4 = new ProductListViewBean();
		   item4.setAvailableQty("40 pcs");
		   item4.setBrandName("Funskool");
		   item4.setImageName(R.drawable.ball);
		   item4.setPrice("$55");
		   item4.setProductName("Ballon");
		   item4.setProductShortDesc("Non plastic ball");
		   productList.add(item4);
		 
		   ProductListViewBean item5 = new ProductListViewBean();
		   item5.setAvailableQty("empty");
		   item5.setBrandName("empty");
		   item5.setImageName(R.drawable.ball);
		   item5.setPrice("empty");
		   item5.setProductName("empty");
		   item5.setProductShortDesc("empty"); 
		   productList.add(item5);
		   return productList;
	 }
	
	/**
	 * Winter List
	 * @return
	 */
   public static ArrayList<ProductListViewBean> getWinterList(){
	   
	   ArrayList<ProductListViewBean> productList = new ArrayList<ProductListViewBean>();
	   
	   ProductListViewBean item1 = new ProductListViewBean();
	   
	   item1.setAvailableQty("10 pcs");
	   item1.setBrandName("Grabber Outdoors");
	   item1.setImageName(R.drawable.blanket_green);
	   item1.setPrice("$35");
	   item1.setProductName("Blanket");
	   item1.setProductShortDesc("Stay Warm in cold conditions");
	   
	   productList.add(item1);
	   
	   ProductListViewBean item2 = new ProductListViewBean();
	   item2.setAvailableQty("20 pcs");
	   item2.setBrandName("Duracell");
	   item2.setImageName(R.drawable.batteries);
	   item2.setPrice("$80");
	   item2.setProductName("Car Batteries");
	   item2.setProductShortDesc("Car Battery for any conditions");
	   productList.add(item2);
	   
	   ProductListViewBean item3 = new ProductListViewBean();
	   item3.setAvailableQty("30 pcs");
	   item3.setBrandName("WolfBike");
	   item3.setImageName(R.drawable.jacket);
	   item3.setPrice("$69");
	   item3.setProductName("Winter jackets");
	   item3.setProductShortDesc("Super light and waterproof. Enjoy your cycling in the wind day");
	   productList.add(item3);
	   
	   ProductListViewBean item4 = new ProductListViewBean();
	   item4.setAvailableQty("40 pcs");
	   item4.setBrandName("Stabilicers");
	   item4.setImageName(R.drawable.icespikes);
	   item4.setPrice("$55");
	   item4.setProductName("Ice Spike");
	   item4.setProductShortDesc("Heavy duty traction for ice, snow crust and river bottoms");
	   productList.add(item4);
	   
	   ProductListViewBean item5 = new ProductListViewBean();
	   item5.setAvailableQty("50 pcs");
	   item5.setBrandName("Kershaw");
	   item5.setImageName(R.drawable.kniphe);
	   item5.setPrice("$99");
	   item5.setProductName("Multi-Function Tool Knife");
	   item5.setProductShortDesc("Bottle opener, screwdriver and lanyard hole"); 
	   productList.add(item5);
	   
	   ProductListViewBean item6 = new ProductListViewBean();
	   item6.setAvailableQty("15 pcs");
	   item6.setBrandName("Rothco");
	   item6.setImageName(R.drawable.rope);
	   item6.setPrice("$99");
	   item6.setProductName("Utility Rope");
	   item6.setProductShortDesc("3/8 Utility Rope;120 lbs working load; 1200psi tensile strength"); 
	   productList.add(item6);
	   
	   ProductListViewBean item7 = new ProductListViewBean();
	   item7.setAvailableQty("empty");
	   item7.setBrandName("empty");
	   item7.setImageName(R.drawable.rope);
	   item7.setPrice("empty");
	   item7.setProductName("empty");
	   item7.setProductShortDesc("empty"); 
	   productList.add(item7);
	   
	   return productList;
	   
   }
   
   public static ArrayList<ProductListViewBean> getBirthDayList(){
	   
	   ArrayList<ProductListViewBean> productList = new ArrayList<ProductListViewBean>();
	   
	   ProductListViewBean item1 = new ProductListViewBean();
	   
	   item1.setAvailableQty("100 pcs");
	   item1.setBrandName("CakesNBakes");
	   item1.setImageName(R.drawable.choco);
	   item1.setPrice("$79");
	   item1.setProductName("Chocolate Cake");
	   item1.setProductShortDesc("This delicious Chocolate Cake is made of Choco cream from Belgium");
	   
	   productList.add(item1);
	   
	   ProductListViewBean item2 = new ProductListViewBean();
	   
	   item2.setAvailableQty("200 pcs");
	   item2.setBrandName("Hot Breads");
	   item2.setImageName(R.drawable.vanilla);
	   item2.setPrice("$80");
	   item2.setProductName("Vanilla Cake");
	   item2.setProductShortDesc("This delicious Vanilla Cake is made of Vanilla cream from Belgium");
	   
	   productList.add(item2);
	   
	   ProductListViewBean item3 = new ProductListViewBean();
	   
	   item3.setAvailableQty("300 pcs");
	   item3.setBrandName("Bakes");
	   item3.setImageName(R.drawable.blacforest);
	   item3.setPrice("$69");
	   item3.setProductName("Blackforest Cake");
	   item3.setProductShortDesc("This delicious Blackforest Cake is made of Blackforest cream from Belgium");
	   
	   productList.add(item3);
	   
	   ProductListViewBean item4 = new ProductListViewBean();
	   
	   item4.setAvailableQty("400 pcs");
	   item4.setBrandName("Baker King");
	   item4.setImageName(R.drawable.strawberry);
	   item4.setPrice("$55");
	   item4.setProductName("Strawberry Cake");
	   item4.setProductShortDesc("This delicious Strawberry Cake is made of Strawberry cream from Belgium");
	   
	   productList.add(item4);
	   
	   ProductListViewBean item5 = new ProductListViewBean();
	   
	   item5.setAvailableQty("500 pcs");
	   item5.setBrandName("Bakes and Bakes");
	   item5.setImageName(R.drawable.pineapple);
	   item5.setPrice("$99");
	   item5.setProductName("Pineapple Cake");
	   item5.setProductShortDesc("This delicious Pineapple Cake is made of Pineapple cream from Belgium");
	   
	   productList.add(item5);
	   
	   ProductListViewBean item7 = new ProductListViewBean();
	   item7.setAvailableQty("empty");
	   item7.setBrandName("empty");
	   item7.setImageName(R.drawable.rope);
	   item7.setPrice("empty");
	   item7.setProductName("empty");
	   item7.setProductShortDesc("empty"); 
	   productList.add(item7);
	
	   return productList;
	   
   }
}
