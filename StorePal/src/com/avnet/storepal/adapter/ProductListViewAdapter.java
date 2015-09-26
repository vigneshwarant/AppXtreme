package com.avnet.storepal.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avnet.storepal.R;
import com.avnet.storepal.beans.ProductListViewBean;

public class ProductListViewAdapter extends ArrayAdapter<ProductListViewBean> {
	
	private final Context context;
	private ArrayList<ProductListViewBean> productList;

	public ProductListViewAdapter(Context context, ArrayList<ProductListViewBean> productList) {
		
		super(context, R.layout.product_list_detail, productList);
		this.context = context;
		this.productList = productList;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View rowView = null; 
	    
	    if(position <= productList.size()-2){
	    	rowView = inflater.inflate(R.layout.product_list_detail, parent, false);
		    TextView productName = (TextView) rowView.findViewById(R.id.productName);
		    TextView productBrand = (TextView) rowView.findViewById(R.id.productBrandName);
		    TextView productDesc = (TextView) rowView.findViewById(R.id.productDesc);
		    TextView qty = (TextView) rowView.findViewById(R.id.productQty);
		    TextView price = (TextView) rowView.findViewById(R.id.productPrice);
		    ImageView productImage = (ImageView) rowView.findViewById(R.id.productImage);
		    
		    Log.i("", "position************:"+position+" "+rowView);
		    
		    
		    ProductListViewBean productBean = productList.get(position);
		    Log.i("", "productBean.getProductName():"+productBean.getProductName());
		    
		    productName.setText(productBean.getProductName());
		    productBrand.setText(productBean.getBrandName());
		    productImage.setImageURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+productBean.getImageName()));
		    productDesc.setText(productBean.getProductShortDesc());
		    qty.setText(productBean.getAvailableQty());
		    price.setText(productBean.getPrice());
	    }
	    else{
	    	rowView = inflater.inflate(R.layout.shopnowbtn_layout, parent, false);
	    }
	    
	    return rowView;
	  }

}
