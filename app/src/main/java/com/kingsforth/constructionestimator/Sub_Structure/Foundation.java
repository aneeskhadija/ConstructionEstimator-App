package com.kingsforth.constructionestimator.Sub_Structure;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;


import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;


public class Foundation extends Fragment {
    private StartAppAd startAppAd = new StartAppAd(getActivity());
	Button btn_stone_work
			,btn_footing
			,btn_pile
			;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.foundation, container, false);
//natiAdView
        AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		btn_stone_work		= (Button) rootView.findViewById(R.id.foundation_stone_work);
		btn_footing			= (Button) rootView.findViewById(R.id.foundation_footing);
		btn_pile			= (Button) rootView.findViewById(R.id.foundation_pile);









        btn_stone_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_slab();
            }
        });






        btn_footing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Footing_quality_selection())
                        .commit();

            }
        });



        btn_pile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_pile();
            }
        });






		return rootView;
	}



    public void dialog_pile(){


        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_column);

        Button btn_cross  			= (Button) dialog.findViewById(R.id.cross);
        Button btn_circular  		= (Button) dialog.findViewById(R.id.circular);
        Button btn_non_circular  	= (Button) dialog.findViewById(R.id.non_circular);

        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




        btn_circular.setText("Circular pile");
        btn_non_circular.setText("Non circular pile");




        btn_circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Pile_circular_quality_selection())
                        .commit();
                dialog.dismiss();
            }
        });


        btn_non_circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Pile_non_circular_selection())
                        .commit();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void dialog_slab(){


        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_foundation);

        Button btn_cross  	= (Button) dialog.findViewById(R.id.cross);
        Button btn_brick  	= (Button) dialog.findViewById(R.id.brick);
        Button btn_stone  	= (Button) dialog.findViewById(R.id.stone);

        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btn_brick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Stone_brick_quality_selection())
                        .commit();
                dialog.dismiss();
            }
        });


        btn_stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new Stone_enter_values())
                        .commit();
                dialog.dismiss();
            }
        });

        dialog.show();

    }






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

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Sub_structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }



}
