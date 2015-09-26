package com.avnet.storepal;

import com.avnet.storepal.beacon.BeaconReferenceApplication1;
import com.avnet.storepal.constants.ListViewConstants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonthlyGroceryFragment extends Fragment {
	
	public MonthlyGroceryFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.monthly_grocery, container, false);
        
        BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1) getActivity().getApplication();
        applicationObj.listName = ListViewConstants.GROCERY_LIST;

         
        return rootView;
    }
}

