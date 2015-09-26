package com.avnet.storepal;

import com.avnet.storepal.beacon.BeaconReferenceApplication1;
import com.avnet.storepal.constants.ListViewConstants;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RandomFragment extends Fragment{

public RandomFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.randomn, container, false);
        Intent i = new Intent(this.getActivity(), Barcode.class);
        startActivity(i);
        
        BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1) getActivity().getApplication();
        applicationObj.listName = ListViewConstants.RANDOM_LIST;

        return rootView;
    }
	
}
