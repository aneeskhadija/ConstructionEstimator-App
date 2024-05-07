package com.kingsforth.constructionestimator.Super_Structure;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Non_circular_selection extends Fragment {

	Button btn_brick
			,btn_rcc
			,btn_cement_block
			;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.column_non_circular_selection, container, false);
		//native adds
		AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		btn_brick			= (Button) rootView.findViewById(R.id.non_brick);
		btn_rcc				= (Button) rootView.findViewById(R.id.non_rcc);
		btn_cement_block	= (Button) rootView.findViewById(R.id.non_cement_block);

		btn_brick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Brick_quality_selection())
						.commit();
			}
		});



		btn_rcc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Non_rcc_quality_selection())
						.commit();
			}
		});




		btn_cement_block.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new Non_cement_block_quality_selection())
						.commit();
			}
		});












		return rootView;
	}







    @Override
    public void onResume() {
        super.onResume();

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
    }                                                                                                                                                                                                                                                                                               public void check(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);


        db_cursor = db_connect.rawQuery("select * from confi_check", null);

        int row_count = db_cursor.getCount();

        db_cursor.moveToFirst();

        if(row_count == 1){

            String check = db_cursor.getString(db_cursor.getColumnIndex("version"));

            if (check.equals("null")){
                getActivity().finish();
            }
        }

        db_connect.close();
    }









}
