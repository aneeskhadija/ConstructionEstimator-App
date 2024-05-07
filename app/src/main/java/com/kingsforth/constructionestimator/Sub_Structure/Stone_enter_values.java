package com.kingsforth.constructionestimator.Sub_Structure;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.InterstitialAd;

public class Stone_enter_values extends Fragment {

	Button btn_nxt;
	ImageView img_nxt;
    EditText ed_length
            ,ed_width
            ,ed_height
            ,ed_percentage;
    InterstitialAd mInterstitialAd;

    double no_of_sakra
            ,price_of_sakra;

    CheckBox chk;

    Boolean check_box=false;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.stone_enter_values, container, false);

		btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
        ed_length       = (EditText) rootView.findViewById(R.id.stone_length);
        ed_width        = (EditText) rootView.findViewById(R.id.stone_width);
        ed_height       = (EditText) rootView.findViewById(R.id.stone_height);
        ed_percentage   = (EditText) rootView.findViewById(R.id.mortar_percentage);

        chk             = (CheckBox) rootView.findViewById(R.id.checkBox);


        ed_percentage.setEnabled(false);


        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chk.isChecked()){
                    ed_percentage.setEnabled(true);
                    check_box = true;
                }
                else {
                    ed_percentage.setEnabled(false);
                    check_box = false;
                }

            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


                if(all_edittext_filled()){

                    if(chk.isChecked()){ // if check box checked

                        if(ed_percentage.getText().toString().trim().equals("")){ // if mortar percentage is setted

                            Toast.makeText(getActivity(), " Enter percentage to proceed.. ", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            calculation_for_percentage();

                        }

                    }else { // if check box is not checked
                        calculation_for_percentage();
                    }



                }



			}
		});

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(all_edittext_filled()){

                    if(chk.isChecked()){ // if check box checked

                        if(ed_percentage.getText().toString().trim().equals("")){ // if mortar percentage is setted

                            Toast.makeText(getActivity(), " Enter percentage to proceed.. ", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            calculation_for_percentage();

                        }

                    }else { // if check box is not checked
                        calculation_for_percentage();
                    }



                }



            }
        });


		return rootView;
	}


    public boolean all_edittext_filled(){


        if(ed_length.getText().toString().trim().equals("") || ed_length.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_length.requestFocus();

            return false;

        }else if(ed_width.getText().toString().trim().equals("") || ed_width.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_width.requestFocus();

            return false;

        }else if(ed_height.getText().toString().trim().equals("") || ed_height.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_height.requestFocus();

            return false;

        }else {

            return true;

        }



    }


    Double send_no_of_sakra
            ,send_price;
    double area;

    public void calculation_for_percentage(){





        area =    Double.parseDouble(ed_length.getText().toString().trim()) ;

        area =    area * Double.parseDouble(ed_width.getText().toString().trim()) ;

        area =    area * Double.parseDouble(ed_height.getText().toString().trim()) ;






        // fetch database
        db_configuration();


        send_no_of_sakra  = area * Double.parseDouble(db_sakra);


        send_price        = send_no_of_sakra * Double.parseDouble(db_price);

        if(chk.isChecked()){
            send_result_to_percentage_activity();

        }else {

            send_result_to_no_percentage_activity();

        }



    }



    public void send_result_to_no_percentage_activity(){


        Stone_result frag = new Stone_result();
        Bundle args = new Bundle();


        args.putString("res_for","no_percentage");
        args.putString("sakra", "" + send_no_of_sakra);
        args.putString("price", ""+send_price);




        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }

    public void send_result_to_percentage_activity(){


        Stone_percentage_quality_selection frag = new Stone_percentage_quality_selection();
        Bundle args = new Bundle();


        args.putString("area", ""+area);
        args.putString("percentage", ed_percentage.getText().toString().trim());

        args.putString("sakra", ""+send_no_of_sakra);
        args.putString("price", ""+send_price);




        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }

    String db_sakra ="0"
            ,db_price ="0"
            ;




    public void db_configuration(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);



        db_cursor = db_connect.rawQuery("select * from configuration_stone", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();


            db_sakra            = db_cursor.getString(db_cursor.getColumnIndex("sakra"));
            db_price            = db_cursor.getString(db_cursor.getColumnIndex("price"));

        }




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
                            .replace(R.id.content_frame, new Foundation())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }


}
