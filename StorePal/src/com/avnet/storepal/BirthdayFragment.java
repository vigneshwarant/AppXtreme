package com.avnet.storepal;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avnet.storepal.adapter.ProductListViewAdapter;
import com.avnet.storepal.beacon.BeaconReferenceApplication1;
import com.avnet.storepal.beacon.MonitoringActivity;
import com.avnet.storepal.constants.ListViewConstants;
import com.avnet.storepal.data.ProductList;

public class BirthdayFragment extends ListFragment {
	
public BirthdayFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View listView = inflater.inflate(R.layout.birthday_list, container, false);
        
        BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1) getActivity().getApplication();
        applicationObj.listName = ListViewConstants.BIRTHDAY_LIST;
        
        ProductListViewAdapter productListAdapter = new ProductListViewAdapter(getActivity().getApplicationContext(), ProductList.getBirthDayList());
        
        setListAdapter(productListAdapter);
         
        return listView;
    }
	
}
