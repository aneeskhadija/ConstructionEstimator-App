package com.kingsforth.constructionestimator.Super_Structure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class Stairs_quality_selection extends Fragment {

    Button btn_nxt;
    ImageView img_nxt;

    RadioButton radio_a
            ,radio_b
            ,radio_c
            ,radio_input;

    String check_radio="";

    EditText ed_sand_input
            ,ed_cement_input
            ,ed_crush_input;

    TextView tv_quality_for;
    InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.three_quality, container, false);


        btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
        radio_a			= (RadioButton) rootView.findViewById(R.id.radio_a);
        radio_b			= (RadioButton) rootView.findViewById(R.id.radio_b);
        radio_c			= (RadioButton) rootView.findViewById(R.id.radio_c);
        radio_input		= (RadioButton) rootView.findViewById(R.id.radio_input);

        ed_sand_input	= (EditText) rootView.findViewById(R.id.sand_input);
        ed_cement_input	= (EditText) rootView.findViewById(R.id.cement_input);
        ed_crush_input	= (EditText) rootView.findViewById(R.id.crush_input);

        tv_quality_for	= (TextView) rootView.findViewById(R.id.quality_for);


        mInterstitialAd = new InterstitialAd(getActivity());

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        ed_cement_input.setEnabled(false);
        ed_sand_input.setEnabled(false);
        ed_crush_input.setEnabled(false);


        tv_quality_for.setText("Stairs ");









//******************   Radio Button Clicks   *************************
        radio_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(true);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                ed_cement_input.setEnabled(false);
                ed_sand_input.setEnabled(false);
                ed_crush_input.setEnabled(false);

                check_radio = "a";
            }
        });




        radio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(true);
                radio_c.setChecked(false);
                radio_input.setChecked(false);

                ed_cement_input.setEnabled(false);
                ed_sand_input.setEnabled(false);
                ed_crush_input.setEnabled(false);

                check_radio = "b";
            }
        });



        radio_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(true);
                radio_input.setChecked(false);

                ed_cement_input.setEnabled(false);
                ed_sand_input.setEnabled(false);
                ed_crush_input.setEnabled(false);

                check_radio = "c";
            }
        });







        radio_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_input.setChecked(true);

                ed_cement_input.setEnabled(true);
                ed_sand_input.setEnabled(true);
                ed_crush_input.setEnabled(true);

                ed_cement_input.findFocus();

                check_radio = "input";

            }
        });

//******************  End Radio Button Clicks   *************************







        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_radio.equals("")){ // if check radio is empty


                    Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();

                }else if (check_radio.equals("input")) {


                    if (ed_sand_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Sand", Toast.LENGTH_SHORT).show();
                        ed_sand_input.findFocus();

                    } else if (ed_cement_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Cement", Toast.LENGTH_SHORT).show();
                        ed_cement_input.findFocus();

                    } else if (ed_crush_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Crush", Toast.LENGTH_SHORT).show();
                        ed_crush_input.findFocus();

                    } else {

                        Stairs_enter_values objFragment = new Stairs_enter_values();

                        Bundle args = new Bundle();

                        args.putString("quality_a", ed_cement_input.getText().toString().trim());
                        args.putString("quality_b", ed_sand_input.getText().toString().trim());
                        args.putString("quality_c", ed_crush_input.getText().toString().trim());

                        objFragment.setArguments(args);

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, objFragment)
                                .commit();
                    }

                } else {  // if check radio is not empty



                    Stairs_enter_values objFragment = new Stairs_enter_values();

                    Bundle args = new Bundle();


                    if (check_radio.equals("a")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "2");
                        args.putString("quality_c", "4");


                    } else if (check_radio.equals("b")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "3");
                        args.putString("quality_c", "6");


                    } else if (check_radio.equals("c")) {

                        args.putString("quality_a", "1");
                        args.putString("quality_b", "4");
                        args.putString("quality_c", "8");


                    }


                    objFragment.setArguments(args);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, objFragment)
                            .commit();



                }

            }
        });

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_radio.equals("")){ // if check radio is empty


                    Toast.makeText(getActivity(), "Select Quality to Proceed..", Toast.LENGTH_SHORT).show();

                }else if (check_radio.equals("input")) {


                    if (ed_sand_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Sand", Toast.LENGTH_SHORT).show();
                        ed_sand_input.findFocus();

                    } else if (ed_cement_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Cement", Toast.LENGTH_SHORT).show();
                        ed_cement_input.findFocus();

                    } else if (ed_crush_input.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Enter quality for Crush", Toast.LENGTH_SHORT).show();
                        ed_crush_input.findFocus();

                    } else {

                        Stairs_enter_values objFragment = new Stairs_enter_values();

                        Bundle args = new Bundle();

                        args.putString("quality_a", ed_cement_input.getText().toString().trim());
                        args.putString("quality_b", ed_sand_input.getText().toString().trim());
                        args.putString("quality_c", ed_crush_input.getText().toString().trim());

                        objFragment.setArguments(args);

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, objFragment)
                                .commit();
                    }

                } else {  // if check radio is not empty



                    Stairs_enter_values objFragment = new Stairs_enter_values();

                    Bundle args = new Bundle();


                    if (check_radio.equals("a")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "2");
                        args.putString("quality_c", "4");


                    } else if (check_radio.equals("b")) {


                        args.putString("quality_a", "1");
                        args.putString("quality_b", "3");
                        args.putString("quality_c", "6");


                    } else if (check_radio.equals("c")) {

                        args.putString("quality_a", "1");
                        args.putString("quality_b", "4");
                        args.putString("quality_c", "8");


                    }


                    objFragment.setArguments(args);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, objFragment)
                            .commit();



                }

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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
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



    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}
