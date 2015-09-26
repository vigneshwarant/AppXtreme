package com.avnet.storepal;

import com.avnet.storepal.beacon.BeaconReferenceApplication1;
import com.avnet.storepal.constants.ListViewConstants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArtFragment extends Fragment {
	
	public ArtFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.art_class_list, container, false);
        
        BeaconReferenceApplication1 applicationObj = (BeaconReferenceApplication1) getActivity().getApplication();
        applicationObj.listName = ListViewConstants.ART_LIST;
        
         
        return rootView;
    }
	
}
