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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Marble_enter_values extends Fragment {

	Button btn_nxt;
	ImageView img_nxt;




    EditText ed_length
            ,ed_width
            ;

    TextView tv_height;

    double cement_quantity_for_send
            , cement_price_for_send
            , sand_quantity_for_send
            , sand_price_for_send
            , crush_quantity_for_send
            , crush_price_for_send
            ;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.marble_enter_values, container, false);

//banner adds
        AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
        ed_length       = (EditText) rootView.findViewById(R.id.s_b_length);
        ed_width        = (EditText) rootView.findViewById(R.id.s_b_width);
        tv_height       = (TextView) rootView.findViewById(R.id.s_b_height);






        // fetch database
        db_configuration();


        tv_height.setText(db_height);



		btn_nxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


                if(all_edittext_filled()){

                    calculation_for_result();


                }



			}
		});

        img_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(all_edittext_filled()){

                    calculation_for_result();


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

        }else {

            return true;

        }



    }









    double area,no_of_marbels,height_in_inches,price_for_send;


    public void calculation_for_result(){







        height_in_inches        =  Double.parseDouble(tv_height.getText().toString().trim());

        height_in_inches  = height_in_inches / 12 ;


        area                =    Double.parseDouble(ed_length.getText().toString().trim()) * Double.parseDouble(ed_width.getText().toString().trim() );


        area                = area * height_in_inches;


        no_of_marbels       = area / db_area;


        price_for_send      = no_of_marbels * Double.parseDouble(db_price);


        Toast.makeText(getActivity(), "no_of_marbels before "+no_of_marbels, Toast.LENGTH_SHORT).show();










        send_result();

    }













    public void send_result(){


        Marbel_result frag = new Marbel_result();
        Bundle args = new Bundle();




        args.putString("no_of_marbels", ""+no_of_marbels);

        args.putString("length", ""+ed_length.getText().toString().trim());
        args.putString("width", ""+ed_width.getText().toString().trim());
        args.putString("height",""+db_height);

        args.putString("price", ""+price_for_send);


        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }















    String db_sand ="0"
            ,db_cement ="0"
            ,db_crush ="0"
            ,db_brick ="0"
            ,db_length ="0"
            ,db_width ="0"
            ,db_height ="0"
            ,db_price ="0"
            ;

    double db_area;



    public void db_configuration(){

        SQLiteDatabase db_connect;
        Cursor db_cursor;

        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);



        db_cursor = db_connect.rawQuery("select * from configuration_first", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();


            db_sand         = db_cursor.getString(db_cursor.getColumnIndex("sand"));
            db_cement       = db_cursor.getString(db_cursor.getColumnIndex("cement"));
            db_crush        = db_cursor.getString(db_cursor.getColumnIndex("coarse"));
            db_brick        = db_cursor.getString(db_cursor.getColumnIndex("brick"));
        }













        db_connect = getActivity().openOrCreateDatabase("smart_estimator",getActivity().MODE_PRIVATE, null);



        db_cursor = db_connect.rawQuery("select * from configuration_marbel", null);


        if(db_cursor.getCount() == 1) {

            db_cursor.moveToNext();


            db_length           = db_cursor.getString(db_cursor.getColumnIndex("length"));
            db_width            = db_cursor.getString(db_cursor.getColumnIndex("width"));
            db_height           = db_cursor.getString(db_cursor.getColumnIndex("height"));
            db_price            = db_cursor.getString(db_cursor.getColumnIndex("price"));

            db_area  = Double.parseDouble(db_length) * Double.parseDouble(db_width) * Double.parseDouble(db_height) ;

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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new Floor_selection())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }



}
