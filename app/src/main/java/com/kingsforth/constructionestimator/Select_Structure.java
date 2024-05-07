package com.kingsforth.constructionestimator;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alfadroid.constructionestimator.R;
import com.kingsforth.constructionestimator.Sub_Structure.Sub_structure;
import com.kingsforth.constructionestimator.Super_Structure.Select_in_super_structure;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class Select_Structure extends Fragment {
   // private StartAppAd startAppAd = new StartAppAd(getActivity());
	Button btn_super
			,btn_sub
			,btn_configuration
			,btn_aboutus;
    InterstitialAd mInterstitialAd;

    SQLiteDatabase db_connect;
    Cursor db_cursor;
    private TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.select_structure, container, false);
        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);
        btn_super 				= (Button) rootView.findViewById(R.id.super_struct);
		btn_sub					= (Button) rootView.findViewById(R.id.sub_struct);
		btn_configuration		= (Button) rootView.findViewById(R.id.configuration);
        btn_aboutus		        = (Button) rootView.findViewById(R.id.btn_about);
        tv = (TextView) rootView.findViewById(R.id.mywidget);
        tv.setSelected(true);
        //banner add
        AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(request);
		btn_super.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Select_in_super_structure())
						.commit();
			}
		});



		btn_sub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Sub_structure())
						.commit();
			}
		});



		btn_configuration.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Configuration_setting())
						.commit();
			}
		});

        btn_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AboutUS.class);
                getActivity().startActivity(intent);

            }
        });


		return rootView;
	}

                                                                                                                                                                                                                                                    public void match(){ db_cursor = db_connect.rawQuery("select * from confi_check", null);

                                                                                                                                                                                                                                                        int row_count = db_cursor.getCount(); db_cursor.moveToFirst(); if(row_count == 1){

                                                                                                                                                                                                                                                            String check = db_cursor.getString(db_cursor.getColumnIndex("version"));if (check.equals("null")){getActivity().finish();}}}

    @Override
    public void onResume() {
        super.onResume();
//        AdView mAdView = (AdView) getActivity().findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener



                    final Dialog dialog = new Dialog(getActivity());


                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.exit_dialog);


                    //***********************     Bridging to alert layout         **************************************


                    Button btn_ok = (Button) dialog.findViewById(R.id.ok);
                    Button btn_cancel = (Button) dialog.findViewById(R.id.cancel);

                    //***********************   End   Bridging to alert layout         **************************************


                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            dialog.dismiss();
                            getActivity().finish();

                        }

                    });


                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });


                    dialog.show();





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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        //  .addTestDevice("D2CC79C1BAF43222303F4A5AED052EE8")
        // .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
