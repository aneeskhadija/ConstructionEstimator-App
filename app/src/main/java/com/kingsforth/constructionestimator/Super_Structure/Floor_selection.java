package com.kingsforth.constructionestimator.Super_Structure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Floor_selection extends Fragment {
	InterstitialAd mInterstitialAd;
	Button btn_chips
			,btn_marble
			,btn_simple
			;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.floor_selection, container, false);

//native adds
		AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		btn_chips			= (Button) rootView.findViewById(R.id.chips);
		btn_marble			= (Button) rootView.findViewById(R.id.marble);
		btn_simple			= (Button) rootView.findViewById(R.id.simple);






        btn_simple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Simple_quality())
						.commit();
			}
		});



		btn_marble.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Marble_enter_values())
						.commit();
			}
		});




		btn_chips.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Chips_quality_selection())
						.commit();
			}
		});












		return rootView;
	}








    @Override
    public void onResume() {
        super.onResume();
//		AdView mAdView = (AdView) getActivity().findViewById(R.id.adView);
//		AdRequest adRequest = new AdRequest.Builder().build();
//		mAdView.loadAd(adRequest);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Select_in_super_structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }



	public void displayInterstitial() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		}
	}


}
