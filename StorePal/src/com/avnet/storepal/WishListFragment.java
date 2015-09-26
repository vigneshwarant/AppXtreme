package com.avnet.storepal;

import com.avnet.storepal.adapter.ProductListViewAdapter;
import com.avnet.storepal.beacon.BeaconReferenceApplication1;
import com.avnet.storepal.constants.ListViewConstants;
import com.avnet.storepal.data.ProductList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WishListFragment extends ListFragment {
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View listView = inflater.inflate(R.layout.wish_list, container, false);
        
        ProductListViewAdapter productListAdapter = new ProductListViewAdapter(getActivity().getApplicationContext(), ProductList.getMyWishList());
        
        setListAdapter(productListAdapter);
        BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1) getActivity().getApplication();
        applicationObj.listName = ListViewConstants.WISH_LIST;
         
        return listView;
    }

}
