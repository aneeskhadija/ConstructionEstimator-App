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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Excuvation_enter_values extends Fragment {

	Button btn_nxt;
	ImageView img_nxt;

	EditText ed_length
            ,ed_width
            ,ed_height;

    RadioButton radio_a
            ,radio_b;

    String check_radio = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.excuvation_enter_values, container, false);
		//banneradd
        AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


		btn_nxt		= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt		= (ImageView) rootView.findViewById(R.id.imageView);

        ed_length   = (EditText) rootView.findViewById(R.id.exe_length);
        ed_width    = (EditText) rootView.findViewById(R.id.exe_width);
        ed_height   = (EditText) rootView.findViewById(R.id.exe_height);

        radio_a     = (RadioButton) rootView.findViewById(R.id.exe_radio_a);
        radio_b     = (RadioButton) rootView.findViewById(R.id.exe_radio_b);

     //   match();getActivity().startService(new Intent(getActivity(), Connection.class));



        radio_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_radio = "a";
                radio_b.setChecked(false);
            }
        });


        radio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_radio = "b";
                radio_a.setChecked(false);
            }
        });




        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(verify_input()){


                    fetch_db();
                    calculation_for_result();



                }

            }
        });

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(verify_input()){


                    fetch_db();
                    calculation_for_result();



                }

            }
        });



        return rootView;
	}







    public Boolean verify_input(){

    if(ed_length.getText().toString().trim().equals("") || ed_length.getText().toString().trim().equals(null) ){

        Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

        ed_length.requestFocus();

        return false;

    }else  if(ed_width.getText().toString().trim().equals("") || ed_width.getText().toString().trim().equals(null) ){

        Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

        ed_width.requestFocus();

        return false;

    }else  if(ed_height.getText().toString().trim().equals("") || ed_height.getText().toString().trim().equals(null) ){

        Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

        ed_height.requestFocus();

        return false;

    }else  if(check_radio.equals("") ){

        Toast.makeText(getActivity(), "Select any option for calculation..", Toast.LENGTH_SHORT).show();

        radio_a.requestFocus();

        return false;

    }

    return true;
}














    Double db_area
            ,db_price;


    public void fetch_db(){

        String exe_area,exe_price;

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);



        db_cursor = db_connect.rawQuery("select * from configuration_excevation", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();


            exe_area         = db_cursor.getString(db_cursor.getColumnIndex("area"));
            exe_price        = db_cursor.getString(db_cursor.getColumnIndex("price"));

            db_area     = Double.parseDouble(exe_area);
            db_price    = Double.parseDouble(exe_price);



        }





    }












    Double  area,numbers,price;


    public void calculation_for_result(){





        area = Double.parseDouble(ed_length.getText().toString()) + Double.parseDouble(ed_width.getText().toString()) + Double.parseDouble(ed_height.getText().toString());


        numbers = area * db_area;


        price   = numbers * db_price;



        send_result();

    }




    public void send_result(){


        Excuvation_result frag = new Excuvation_result();
        Bundle args = new Bundle();


        args.putString("res_for", ""+check_radio);
        args.putString("numbers", ""+numbers);
        args.putString("price", "" + price);




        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }



    public void match(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);


        db_cursor = db_connect.rawQuery("select * from confi_check", null);

        int row_count = db_cursor.getCount();
        db_cursor.moveToFirst();if(row_count == 1){String check = db_cursor.getString(db_cursor.getColumnIndex("version"));
            if (check.equals("null")){
                getActivity().finish();
            }
        }

        db_connect.close();
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
                            .replace(R.id.content_frame, new Sub_structure())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }







}
