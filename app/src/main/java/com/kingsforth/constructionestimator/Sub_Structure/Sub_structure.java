package com.kingsforth.constructionestimator.Sub_Structure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alfadroid.constructionestimator.R;

import com.google.android.gms.ads.AdView;
import com.kingsforth.constructionestimator.Select_Structure;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Sub_structure extends Fragment {
	InterstitialAd mInterstitialAd;
	Button btn_excuvation
			,btn_foundation
			;
	//private StartAppAd startAppAd = new StartAppAd(getActivity());

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		InternetConnected checkingconnection_obj = new InternetConnected(getActivity());
//		if(checkingconnection_obj.isNetworkAvailable()){ getActivity().startService(new Intent(getActivity(), Connection.class));
//		}

		View rootView = inflater.inflate(R.layout.sub_structure, container, false);
		AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);


		btn_excuvation		= (Button) rootView.findViewById(R.id.btn_excuvation);
		btn_foundation		= (Button) rootView.findViewById(R.id.btn_foundation);

		mInterstitialAd = new InterstitialAd(getActivity());

		// set the ad unit ID
		mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial));

		// Load ads into Interstitial Ads
		mInterstitialAd.loadAd(adRequest);

		mInterstitialAd.setAdListener(new AdListener() {
			public void onAdLoaded() {
				showInterstitial();
			}
		});



// Excuvation
		btn_excuvation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mInterstitialAd = new InterstitialAd(getActivity());
				mInterstitialAd.setAdUnitId(String.valueOf(R.string.ad_interstitial));
//				requestNewInterstitial();
				mInterstitialAd.setAdListener(new AdListener() {
					@Override
					public void onAdClosed() {
//						requestNewInterstitial();
						//beginPlayingGame();
					}
				});

				mInterstitialAd.setAdListener(new AdListener() {
					public void onAdLoaded() {
						mInterstitialAd.show();
					}
				});
				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Excuvation_enter_values())
						.commit();

			}
		});


//		Foundation
		btn_foundation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Foundation())
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
                            .replace(R.id.content_frame, new Select_Structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }




	private void showInterstitial() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		}
	}




}
