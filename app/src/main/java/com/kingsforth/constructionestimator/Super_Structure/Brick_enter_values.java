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
import android.widget.Toast;

import com.alfadroid.constructionestimator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Brick_enter_values extends Fragment {

	Button btn_nxt;
	ImageView img_nxt;


	String sand_quality
			,cement_quality
			,crush_quality;

    EditText ed_length
            ,ed_width
            ,ed_height
            ;

    double cement_quantity_for_send
            , cement_price_for_send
            , sand_quantity_for_send
            , sand_price_for_send
            , crush_quantity_for_send
            , crush_price_for_send
            , no_of_bricks
            , price_of_bricks;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.non_circular_brick_enter_values, container, false);

//banner adds
        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

		btn_nxt			= (Button) rootView.findViewById(R.id.btn_next);
        img_nxt			= (ImageView) rootView.findViewById(R.id.imageView);
        ed_length       = (EditText) rootView.findViewById(R.id.s_b_length);
        ed_width        = (EditText) rootView.findViewById(R.id.s_b_width);
        ed_height       = (EditText) rootView.findViewById(R.id.s_b_height);





        cement_quality  		= getArguments().getString("quality_a");
		sand_quality    		= getArguments().getString("quality_b");




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

        }else if(ed_height.getText().toString().trim().equals("") || ed_height.getText().toString().trim().equals(null) ){

            Toast.makeText(getActivity(), "Please fill the form to proceed..", Toast.LENGTH_SHORT).show();

            ed_height.requestFocus();

            return false;

        }else {

            return true;

        }



    }                                                                                                                                                                                                                                                                                                                                                                                           public void check(){

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














    public void calculation_for_result(){


        double area,result;


        area =    Double.parseDouble(ed_length.getText().toString().trim()) * Double.parseDouble(ed_width.getText().toString().trim()) * Double.parseDouble(ed_height.getText().toString().trim() );


        result = ( area * 30 ) / 100;

        result = result * 1.54;















        // Ratio

        double quality_a = Double.parseDouble(cement_quality)
                ,quality_b = Double.parseDouble(sand_quality)
                ;





        double sum_of_ratio = quality_a + quality_b ;





        cement_quantity_for_send =  ( quality_a / sum_of_ratio ) * result;

        cement_quantity_for_send = cement_quantity_for_send / 1.25;



        sand_quantity_for_send =  ( quality_b / sum_of_ratio ) * result;

        sand_quantity_for_send = sand_quantity_for_send / 100;







        // fetch database
        db_configuration();




        cement_price_for_send    = cement_quantity_for_send * Double.parseDouble(db_cement);

        sand_price_for_send      = sand_quantity_for_send * Double.parseDouble(db_sand);




        no_of_bricks       = area * 13.5;

        price_of_bricks    = no_of_bricks * Double.parseDouble(db_brick);


        send_result();

    }





    public void send_result(){


        Brick_result frag = new Brick_result();
        Bundle args = new Bundle();


        args.putString("sand_ratio", sand_quality);
        args.putString("cement_ratio", cement_quality);

        args.putString("cement_quantity", ""+cement_quantity_for_send);
        args.putString("cement_price", ""+cement_price_for_send);

        args.putString("sand_quantity", ""+sand_quantity_for_send);
        args.putString("sand_price",""+sand_price_for_send);

        args.putString("no_of_bricks", "" +no_of_bricks);
        args.putString("price_of_bricks", ""+price_of_bricks);



        frag.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();



    }




    String db_sand ="0"
            ,db_cement ="0"
            ,db_crush ="0"
            ,db_brick ="0"
            ;




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
                            .replace(R.id.content_frame, new Brick_quality_selection())
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }

}
